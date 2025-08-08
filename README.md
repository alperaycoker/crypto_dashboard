# 💹 Event-Driven Crypto Monitoring Dashboard

Crypto Dashboard, gerçek zamanlı kripto para fiyatlarını izleyen, anomali tespiti yapan ve yüksek oynaklık durumlarını analiz ederek kullanıcıya WebSocket üzerinden canlı veri sunan, olay bazlı mikroservis mimarisiyle inşa edilmiş kapsamlı bir dashboard projesidir.

---

## 🧠 Genel Mimarî: Event-Driven Microservices

Bu proje, **Olay Bazlı Mikroservis Mimarisi** kullanarak yüksek performanslı ve esnek bir sistem tasarımı sunar. Sistemdeki servisler, doğrudan birbiriyle konuşmak yerine, merkezi bir mesajlaşma sistemi (Apache Kafka) üzerinden haberleşir. Bu yapı sayesinde her servis bağımsız geliştirilebilir ve sistemin bir parçasında yapılan değişiklik diğer parçaları etkilemez.

Örneğin, veri sağlayıcısı olan `CryptoCompare API` değiştirilmek istenirse, yalnızca Python producer servisinde güncelleme yapılır; ksqlDB ya da Spring Boot backend bu durumdan etkilenmez.

---

## 📊 Mimarî Diyagram

<img width="2998" height="182" alt="kripto_gozcu_mimari" src="https://github.com/user-attachments/assets/fdc5ac28-55b5-421d-882d-2bdf87d89af8" />


## 🔄 Verinin Yolculuğu

### 0. Docker (Sistem Orkestrasyonu)
Görevi: Tüm servisleri izole bir şekilde ayağa kaldırmak ve aynı ortamda çalıştırmak.

Nasıl Çalışır?
Projede tanımlı **docker-compose.yml** dosyası ile aşağıdaki tüm servisler tek komutla başlatılır:

**Python Producer**

**Apache Kafka (broker, zookeeper)**

**ksqlDB**

**Spring Boot Backend**

**React (Next.js) frontend (isteğe bağlı)**

### 1. Python Producer (Veri Üretimi)

CryptoCompare API'den belirli aralıklarla fiyat verisi alır.

JSON formatında ({ symbol, price, timestamp }) Kafka’ya gönderir.

Kafka topic: crypto_forex_raw

### 2. Apache Kafka (Veri Taşıma)

Tüm veri akışının merkezidir.

Verileri farklı topic’lere dağıtarak servisler arası bağımsızlığı sağlar.

### 3. ksqlDB (Veri Analizi)
SQL benzeri sorgularla anlık hesaplamalar yapar:

Ortalama fiyat hesaplama
5 dakikalık volatilite analizi
Anomali (ani fiyat sıçraması) tespiti
Kafka’ya analiz sonuçlarını yazar:

PRICE_SPIKE_ALERTS
volatility_5min

###  4. Spring Boot Backend (Veri Servis Etme)
Kafka’dan gelen verileri dinler.

Verileri WebSocket kanallarına aktarır:

/topic/prices

/topic/alerts

/topic/volatility

###  5. React UI (Veri Görselleştirme)
Kullanıcının tarayıcısında çalışır.

WebSocket ile bağlanarak canlı veri alır.

Gerçek zamanlı tablo, grafik ve ısı haritası sunar.

##  🧱 Kullanılan Teknolojiler ve Amaçları

**Docker**:	Tüm servisleri izole çalıştırmak ve dev ortamını tek tıkla ayağa kaldırmak

**Python**:	Veri üretici (producer)** olarak görev alır, API'den veri çeker

**Apache Kafka**:	Servisler arası dayanıklı ve asenkron iletişim sağlar

**ksqlDB**:	Akan veri üzerinde SQL ile analiz yapar

**Spring Boot	Backend**: sunucusu, Kafka’dan gelen verileri WebSocket ile sunar

**React**:	Gerçek zamanlı, bileşen tabanlı kullanıcı arayüzü oluşturur


##  ⚙️ Kurulum (Docker ile)

### Proje klasörüne gir

cd crypto_dashboard

### Docker konteynerlerini başlat

docker-compose up --build

Her servis kendi konteynerinde ayağa kalkar:

Python Producer → localhost:8000

Kafka Broker → localhost:9092

ksqlDB UI → localhost:8088

Spring Boot → localhost:8080

React UI → localhost:3000

## 📈 Gerçek Zamanlı Özellikler

#### 📊 Fiyat Tablosu – Canlı kripto para fiyatları

#### 📉 Oynaklık Analizi – Kısa vadeli volatilite takibi

#### ⚠️ Anomali Uyarıları – Ani fiyat sıçramalarının tespiti

#### 🌐 WebSocket Altyapısı – Tek bağlantı üzerinden çoklu kanal dinleme

### 📁 Proje Yapısı

crypto_dashboard/

├── producer-python/

├── backend-springboot/

├── dashboard-ui/ (Next.js)

├── docker-compose.yml

└── README.md


<img width="1890" height="818" alt="image" src="https://github.com/user-attachments/assets/f03422a5-f332-43e6-a6ab-8b9bc0e42f50" />
<img width="1892" height="663" alt="image" src="https://github.com/user-attachments/assets/a897f071-1175-4d6f-8842-33df759baef3" />



📄 Lisans
MIT License © Alp Eray Çoker

### ✍️ Medium Makalesi (Yakında)
