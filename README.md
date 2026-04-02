# 💹 Event-Driven Crypto Monitoring Dashboard

**Crypto Dashboard**, gerçek zamanlı kripto para fiyatlarını izleyen, anomali tespiti yapan ve yüksek volatilite durumlarını analiz ederek kullanıcıya WebSocket üzerinden canlı veri sunan kapsamlı bir izleme sistemidir. Modern **Olay Güdümlü Mikroservis Mimarisi (Event-Driven Microservices)** kullanılarak geliştirilmiştir.

---

## 🧠 Genel Mimari: Olay Güdümlü Mikroservisler

Bu proje, yüksek performanslı ve esnek bir sistem tasarımı sunar. Sistemdeki servisler, doğrudan birbiriyle konuşmak yerine merkezi bir olay akış platformu (**Apache Kafka**) üzerinden haberleşir. Bu gevşek bağlı (loosely coupled) yapı sayesinde:
* Her servis bağımsız olarak ölçeklenebilir ve geliştirilebilir.
* Sistem bileşenlerinden birinde yapılan değişiklik diğerlerini etkilemez (Örn: Veri sağlayıcısı değiştirilmek istendiğinde sadece Producer servisi güncellenir).

### 📊 Mimari Diyagram

<img width="2998" height="182" alt="kripto_gozcu_mimari" src="https://github.com/user-attachments/assets/fdc5ac28-55b5-421d-882d-2bdf87d89af8" />

---

## 🔄 Sistem Bileşenleri ve Veri Akışı

Sistem, **Docker** tarafından izole konteynerler halinde orkestre edilir ve aşağıdaki veri akış döngüsünü takip eder:

1. **🐍 Python Producer (Veri Üretimi):** 
   - CryptoCompare API'den belirli aralıklarla fiyat verisi çeker.
   - Veriyi JSON formatında (`{ symbol, price, timestamp }`) Kafka’daki `crypto_forex_raw` topic'ine iletir.
2. **🐿️ Apache Kafka (Veri Taşıma):** 
   - Tüm veri akışının merkezidir. Gelen ham verileri ve işlenmiş metrikleri ilgili topic'lerde güvenle barındırır.
3. **⚙️ ksqlDB (Gerçek Zamanlı Analiz):** 
   - Akan veri (stream) üzerinde SQL benzeri sorgularla anlık hesaplamalar yapar.
   - Ortalama fiyatı ve 5 dakikalık volatiliteyi hesaplar.
   - Ani fiyat sıçramalarını (anomalileri) tespit ederek sonuçları Kafka'daki `PRICE_SPIKE_ALERTS` ve `volatility_5min` topic'lerine yazar.
4. **🍃 Spring Boot Backend (Veri Dağıtımı):** 
   - Kafka’dan gelen işlenmiş verileri ve anomalileri dinler.
   - İstemcilere iletmek üzere verileri WebSocket kanallarına (`/topic/prices`, `/topic/alerts`, `/topic/volatility`) aktarır.
5. **⚛️ React / Next.js UI (Görselleştirme):** 
   - Kullanıcının tarayıcısında çalışır ve WebSocket üzerinden canlı veri akışına bağlanır.
   - Gerçek zamanlı tablolar, grafikler ve uyarı bildirimleri sunar.

---

## 🧱 Teknoloji Yığını

| Teknoloji | Katman | Görev / Kullanım Amacı |
| :--- | :--- | :--- |
| **Docker & Compose** | DevOps / Orkestrasyon | Tüm servisleri izole çalıştırmak ve geliştirme ortamını tek tıkla başlatmak. |
| **Python** | Veri Üretici (Producer) | Dış API'lerden veri çekmek ve sisteme entegre etmek. |
| **Apache Kafka** | Mesajlaşma / Streaming | Servisler arası dayanıklı, yüksek performanslı ve asenkron iletişim. |
| **ksqlDB** | Veri İşleme (Stream Processing) | Akan veri üzerinde anlık analiz, filtreleme ve anomali tespiti. |
| **Spring Boot (Java)** | Backend (Sunucu) | Kafka entegrasyonu ve WebSocket üzerinden verilerin istemciye sunulması. |
| **React / Next.js** | Frontend (Kullanıcı Arayüzü) | Gerçek zamanlı, modern ve reaktif bir dashboard oluşturmak. |

---

## 🚀 Başlangıç ve Kurulum

Projeyi yerel ortamınızda çalıştırmak için sisteminizde **Docker** ve **Docker Compose** kurulu olmalıdır.

<img width="1897" height="728" alt="image" src="https://github.com/user-attachments/assets/d6303735-fb3f-4711-84de-8a2e810b0a18" />

<img width="1892" height="754" alt="image" src="https://github.com/user-attachments/assets/c38c4672-3f48-45ae-abbd-7b2ac3f8cd61" />

**1. Projeyi Klonlayın:**
```bash
git clone [https://github.com/KULLANICI_ADINIZ/crypto_dashboard.git](https://github.com/KULLANICI_ADINIZ/crypto_dashboard.git)
cd crypto_dashboard
