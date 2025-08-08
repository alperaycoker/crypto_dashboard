import time
import json
import requests
import os
from kafka import KafkaProducer
from kafka.errors import NoBrokersAvailable

print("Python Producer (CryptoCompare) script'i başlatılıyor...")

# Isı haritasına uygun olması için coin listesi
COINS = "BTC,ETH,SOL,XRP,ADA,DOGE,AVAX,DOT,MATIC,TRX,LTC"
CURRENCIES = "USD" # Sadece USD paritesi
API_URL = f"https://min-api.cryptocompare.com/data/pricemulti?fsyms={COINS}&tsyms={CURRENCIES}"

API_KEY = os.environ.get('CRYPTOCOMPARE_API_KEY')
if not API_KEY or API_KEY == "BURAYA_CRYPTOCOMPARE_API_KEY_I_YAPISTIRIN":
    raise ValueError("CRYPTOCOMPARE_API_KEY ortam değişkeni ayarlanmamış!")

HEADERS = {"authorization": f"Apikey {API_KEY}"}
KAFKA_TOPIC = "crypto_forex_raw"
KAFKA_SERVER = "kafka:29092"

def create_kafka_producer():
    retries = 10
    while retries > 0:
        try:
            producer = KafkaProducer(
                bootstrap_servers=KAFKA_SERVER,
                value_serializer=lambda v: json.dumps(v).encode('utf-8')
            )
            print("Kafka Producer'a başarıyla bağlanıldı.")
            return producer
        except NoBrokersAvailable:
            retries -= 1
            print(f"Kafka broker'ları bulunamadı. {retries} deneme kaldı. 10 saniye içinde tekrar denenecek...")
            time.sleep(10)
    raise RuntimeError("Tüm denemelerden sonra Kafka'ya bağlanılamadı.")

producer = create_kafka_producer()
print("Python Producer veri göndermeye başlıyor...")

while True:
    try:
        response = requests.get(API_URL, headers=HEADERS, timeout=15)
        response.raise_for_status()
        data = response.json()

        if "Response" in data and data["Response"] == "Error":
            print(f"API Hatası Alındı: {data['Message']}")
        else:
            for coin, prices in data.items():
                for currency, price in prices.items():
                    message = {
                        "symbol": f"{coin}/{currency}",
                        "price": price,
                        "timestamp": int(time.time() * 1000)
                    }
                    producer.send(KAFKA_TOPIC, value=message)
                    print(f"Gönderildi: {message}")
        
        producer.flush()

    except requests.exceptions.HTTPError as http_err:
        print(f"HTTP Hatası Alındı: {http_err}")
        if response.status_code == 429:
            print("Rate limit aşıldı, 70 saniye bekleniyor...")
            time.sleep(70)
    except Exception as e:
        print(f"Genel bir hata oluştu: {e}")

    time.sleep(10)
