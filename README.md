Crypto Dashboard

Gerçek zamanlı kripto para verilerini işleyen, analiz eden ve anomali/volatilite uyarıları sunan Docker tabanlı mikroservis mimarisi.

📈 Genel Mimari

Bileşenler:

CryptoCompare API: Harici veri sağlayıcı.

Python Producer: API'den verileri alır ve Kafka'ya yollar.

Apache Kafka: Veri iletim kuyruklarını yönetir.

ksqlDB: Gerçek zamanlı stream sorguları yapar.

Spring Boot Backend: İlgili Kafka konularını dinler ve frontend'e websocket ile veri yollar.

React / Next.js Frontend: Gerçek zamanlı arayüz.

Ngrok Tunnel: Frontend'e internet üzerinden erişimi sağlar.

🚀 Kurulum

Gereksinimler

Docker & Docker Compose

Ngrok hesabı (frontend yayını için)

Adımlar:

API servisi sağlayacısından api key'i edinmek ve "docker-compose.yml" dosyasında ilgili yere yapıştırmak.

git clone https://github.com/kullaniciadi/crypto_dashboard //
cd crypto_dashboard //
docker-compose up --build

Ngrok ile frontend'i yayına almak:

ngrok http 3000

🌐 Kullanılan Teknolojiler

Python (Producer)

Apache Kafka

ksqlDB

Spring Boot

React / Next.js

Docker & Docker Compose

Ngrok

⚙️ Kafka Topic Yapısı

crypto_forex_raw: Ham veriler

PRICE_SPIKE_ALERTS: Anomali tespiti

volatility_5min: 5 dakikalık volatilite verisi

✅ Özellikler

Gerçek zamanlı fiyat verisi

Stream tabanlı analiz

Anomali tespiti

Websocket ile frontend entegrasyonu

Taşınabilir mikroservis mimarisi


<img width="1918" height="730" alt="image" src="https://github.com/user-attachments/assets/91bfd52c-4881-4cc8-9e46-8365d075c8a9" />
<img width="1900" height="745" alt="image" src="https://github.com/user-attachments/assets/18cdc230-522a-4e69-ab85-8b37a87303a3" />


