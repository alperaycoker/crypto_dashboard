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

İlgili api servisi sağlayacısından api key'i edinmek ve "docker-compose.yml" dosyasında ilgili yere yapıştırmak.

git clone https://github.com/kullaniciadi/crypto_dashboard
cd crypto_dashboard
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


<img width="1911" height="868" alt="image" src="https://github.com/user-attachments/assets/cdd51782-3dc8-4321-a675-13e69a1d9dad" />

<img width="1897" height="738" alt="image" src="https://github.com/user-attachments/assets/68633740-a2b3-461f-97fe-f36b9ba5a6da" />

