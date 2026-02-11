-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: economerce
-- ------------------------------------------------------
-- Server version	9.1.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `brand_category`
--

DROP TABLE IF EXISTS `brand_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `brand_category` (
  `brand_id` bigint NOT NULL,
  `category_id` bigint NOT NULL,
  KEY `FKf7d8fhxmc6fnv5o94hfp593md` (`category_id`),
  KEY `FKi8jpf7jlc4yv4f5lieolmlly9` (`brand_id`),
  CONSTRAINT `FKf7d8fhxmc6fnv5o94hfp593md` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`),
  CONSTRAINT `FKi8jpf7jlc4yv4f5lieolmlly9` FOREIGN KEY (`brand_id`) REFERENCES `brands` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brand_category`
--

LOCK TABLES `brand_category` WRITE;
/*!40000 ALTER TABLE `brand_category` DISABLE KEYS */;
INSERT INTO `brand_category` VALUES (3,1),(1,1),(1,2),(4,2),(5,3),(6,3),(7,3),(8,4),(9,4),(10,5),(11,6),(12,7),(2,1),(2,2),(2,5),(2,6),(2,7);
/*!40000 ALTER TABLE `brand_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `brands`
--

DROP TABLE IF EXISTS `brands`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `brands` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `brand_name` varchar(255) DEFAULT NULL,
  `url_image_brand` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brands`
--

LOCK TABLES `brands` WRITE;
/*!40000 ALTER TABLE `brands` DISABLE KEYS */;
INSERT INTO `brands` VALUES (1,'Apple','uploads/Brand/1770039705794_brandIphone.png'),(2,'Samsung','uploads/Brand/1770039730101_brandSamsung.png'),(3,'Xiaomi','uploads/Brand/1770039748710_brandXiaomi.png'),(4,'Huwei','uploads/Brand/1770040170999_brandHuwei.png'),(5,'Macbook','uploads/Brand/1770040390809_brandMacbook.png'),(6,'Asus','uploads/Brand/1770040404552_brandAsus.png'),(7,'Dell','uploads/Brand/1770040420575_brandDell.png'),(8,'Airport','uploads/Brand/1770040637607_brandAirport.png'),(9,'Samsug','uploads/Brand/1770040659595_brandSamsung.png'),(10,'AppWatch','uploads/Brand/1770040745303_brandWatch.png'),(11,'LG','uploads/Brand/1770040854532_brandLG.png'),(12,'Sony','uploads/Brand/1770040989282_brandSony.png');
/*!40000 ALTER TABLE `brands` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_items`
--

DROP TABLE IF EXISTS `cart_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_items` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `category_id` bigint DEFAULT NULL,
  `product_price` double DEFAULT NULL,
  `quantity` int NOT NULL,
  `total_price` double NOT NULL,
  `cart_id` bigint DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  `product_color_id` bigint DEFAULT NULL,
  `product_variant_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpcttvuq4mxppo8sxggjtn5i2c` (`cart_id`),
  KEY `FK1re40cjegsfvw58xrkdp6bac6` (`product_id`),
  KEY `FKjpht3d0dgsandtpdevh06qb42` (`product_color_id`),
  KEY `FKr2nc5fmwsdlfgttqko5t8sgtb` (`product_variant_id`),
  CONSTRAINT `FK1re40cjegsfvw58xrkdp6bac6` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  CONSTRAINT `FKjpht3d0dgsandtpdevh06qb42` FOREIGN KEY (`product_color_id`) REFERENCES `product_color` (`id`),
  CONSTRAINT `FKpcttvuq4mxppo8sxggjtn5i2c` FOREIGN KEY (`cart_id`) REFERENCES `carts` (`cart_id`),
  CONSTRAINT `FKr2nc5fmwsdlfgttqko5t8sgtb` FOREIGN KEY (`product_variant_id`) REFERENCES `product_variant` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_items`
--

LOCK TABLES `cart_items` WRITE;
/*!40000 ALTER TABLE `cart_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carts`
--

DROP TABLE IF EXISTS `carts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carts` (
  `cart_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`cart_id`),
  UNIQUE KEY `UK64t7ox312pqal3p7fg9o503c2` (`user_id`),
  CONSTRAINT `FKb5o626f86h46m4s7ms6ginnop` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carts`
--

LOCK TABLES `carts` WRITE;
/*!40000 ALTER TABLE `carts` DISABLE KEYS */;
INSERT INTO `carts` VALUES (1,1);
/*!40000 ALTER TABLE `carts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'Điện Thoại'),(2,'Tablet'),(3,'Laptop'),(4,'Tai nghe'),(5,'Đồng Hồ'),(6,'Màn Hình'),(7,'Tivi');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_item`
--

DROP TABLE IF EXISTS `order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_item` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `caterory_id` bigint DEFAULT NULL,
  `price_buy` double DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `order_id` bigint DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  `product_color_id` bigint DEFAULT NULL,
  `product_variant_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKt4dc2r9nbvbujrljv3e23iibt` (`order_id`),
  KEY `FKc5uhmwioq5kscilyuchp4w49o` (`product_id`),
  KEY `FKbtxf5g8lvyw7pgwq713rtgagh` (`product_color_id`),
  KEY `FKasbjwtdare2wb3anogb1oai26` (`product_variant_id`),
  CONSTRAINT `FKasbjwtdare2wb3anogb1oai26` FOREIGN KEY (`product_variant_id`) REFERENCES `product_variant` (`id`),
  CONSTRAINT `FKbtxf5g8lvyw7pgwq713rtgagh` FOREIGN KEY (`product_color_id`) REFERENCES `product_color` (`id`),
  CONSTRAINT `FKc5uhmwioq5kscilyuchp4w49o` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  CONSTRAINT `FKt4dc2r9nbvbujrljv3e23iibt` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item`
--

LOCK TABLES `order_item` WRITE;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
INSERT INTO `order_item` VALUES (1,1,16790000,1,1,3,8,8),(2,1,19890000,1,2,3,7,9),(3,1,16790000,1,3,3,7,8),(4,1,62990000,1,4,1,1,4),(5,1,26290000,1,5,4,15,11),(6,1,43990000,1,6,2,6,7),(7,1,43990000,1,7,2,4,7),(8,1,43990000,1,8,2,5,7),(9,1,62990000,1,9,1,1,4),(10,1,26290000,1,10,4,15,11),(11,1,62990000,1,11,1,1,4),(12,1,62990000,1,12,1,1,4);
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `day_create` datetime(6) NOT NULL,
  `order_name` varchar(255) DEFAULT NULL,
  `payment_method` enum('COD','VNPAY') DEFAULT NULL,
  `payment_status` enum('PAID','REFUNDED','UNPAID') DEFAULT NULL,
  `status` enum('CANCELLED','CONFIRMED','DELIVERED','PENDING','SHIPPING') DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK32ql8ubntj5uh44ph9659tiih` (`user_id`),
  CONSTRAINT `FK32ql8ubntj5uh44ph9659tiih` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'2026-02-05 19:09:11.887653','iPhone 15 Plus | Chính hãng VN/A ','COD','UNPAID','CANCELLED',16790000,1),(2,'2026-02-05 19:08:15.260057','iPhone 15 Plus | Chính hãng VN/A ','COD','UNPAID','CANCELLED',19890000,1),(3,'2026-02-04 21:29:39.127045','iPhone 15 Plus | Chính hãng VN/A ','COD','UNPAID','PENDING',16790000,1),(4,'2026-02-04 22:06:18.345688','iPhone 17 Pro Max | Chính hãng','COD','UNPAID','PENDING',62990000,1),(5,'2026-02-04 22:12:37.638051','iPhone 15 Pro  | Chính hãng VN/A ','COD','UNPAID','PENDING',26290000,1),(6,'2026-02-04 22:19:50.437525','iPhone 16 Pro Max | Chính hãng VN/A','COD','UNPAID','PENDING',43990000,1),(7,'2026-02-04 23:03:28.426472','iPhone 16 Pro Max | Chính hãng VN/A','COD','UNPAID','PENDING',43990000,1),(8,'2026-02-04 23:13:40.768006','iPhone 16 Pro Max | Chính hãng VN/A','COD','UNPAID','PENDING',43990000,1),(9,'2026-02-04 23:34:34.228112','iPhone 17 Pro Max | Chính hãng','COD','UNPAID','PENDING',62990000,1),(10,'2026-02-05 05:16:06.574827','iPhone 15 Pro  | Chính hãng VN/A ','COD','UNPAID','PENDING',26290000,1),(11,'2026-02-05 06:09:41.451492','iPhone 17 Pro Max | Chính hãng','COD','UNPAID','PENDING',62990000,1),(12,'2026-02-05 17:26:59.898601','iPhone 17 Pro Max | Chính hãng','VNPAY','PAID','PENDING',62990000,1);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_color`
--

DROP TABLE IF EXISTS `product_color`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_color` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title_variant` varchar(255) DEFAULT NULL,
  `url_photo` varchar(255) DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjs0ht7btbgt5u0jpossmgvfk5` (`product_id`),
  CONSTRAINT `FKjs0ht7btbgt5u0jpossmgvfk5` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_color`
--

LOCK TABLES `product_color` WRITE;
/*!40000 ALTER TABLE `product_color` DISABLE KEYS */;
INSERT INTO `product_color` VALUES (1,'Cam vũ trụ','uploads/productColor/1770042407697_colorCamVuTru.png',1),(2,'Bạc','uploads/productColor/1770042478725_colorBac.png',1),(3,'Xanh Đậm','uploads/productColor/1770042531537_colorXanhDam.png',1),(4,'Titan Sa Mạc','uploads/productColor/1770125015450_colorTitanSamac.png',2),(5,'Titan Tự Nhiên','uploads/productColor/1770125159498_colorTitanTuNhien.png',2),(6,'Titan Đen','uploads/productColor/1770125178738_colorTitanDen.png',2),(7,'Hồng','uploads/productColor/1770126048735_colorPink.png',3),(8,'Vàng','uploads/productColor/1770126065237_colorYellow.png',3),(9,'Xanh Dương','uploads/productColor/1770126082039_colorBlue.png',3),(10,'Xanh lá','uploads/productColor/1770126100446_colorGreen.png',3),(11,'Đen','uploads/productColor/1770126109088_colorBlack.png',3),(12,'Titan Tự Nhiên','uploads/productColor/1770127856379_colorTitanTuNhien.png',4),(13,'Titan Đen','uploads/productColor/1770128082433_colorTitanDen.png',4),(14,'Titan Trắng','uploads/productColor/1770128094633_colorTitanTrang.png',4),(15,'TiTan Xanh','uploads/productColor/1770128119737_colorTitanXanh.png',4),(16,'Đen','uploads/productColor/1770128787544_colorBlack.png',5),(17,'Bạc','uploads/productColor/1770128800681_colorSilver.png',5),(18,'Tím','uploads/productColor/1770128813799_colorPurpel.png',5),(19,'Vàng','uploads/productColor/1770128826336_colorYellow.png',5),(20,'Đen','uploads/productColor/1770129625248_colorBlack.png',6),(21,'Đỏ','uploads/productColor/1770129638519_colorRed.png',6),(22,'Hồng','uploads/productColor/1770129646913_colorPink.png',6),(23,'Xanh lá','uploads/productColor/1770129658051_colorGreen.png',6),(24,'Xanh dương','uploads/productColor/1770129668590_colorBlue.png',6),(25,'Trắng','uploads/productColor/1770129683030_colorWhite.png',6),(26,'Xanh','uploads/productColor/1770385191114_colorBlue.png',7),(27,'Vàng','uploads/productColor/1770385209239_colorPink.png',7),(28,'Đen','uploads/productColor/1770385681265_colorBlack.png',8),(29,'Bạc','uploads/productColor/1770385703845_colorSilver.png',8);
/*!40000 ALTER TABLE `product_color` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_product_specification`
--

DROP TABLE IF EXISTS `product_product_specification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_product_specification` (
  `product_specification_id` bigint NOT NULL,
  `product_id` bigint NOT NULL,
  KEY `FK6qv46d59snp9thlo0xquxgep2` (`product_id`),
  KEY `FK8iq79vn87id0rpj20a2ujpdt3` (`product_specification_id`),
  CONSTRAINT `FK6qv46d59snp9thlo0xquxgep2` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  CONSTRAINT `FK8iq79vn87id0rpj20a2ujpdt3` FOREIGN KEY (`product_specification_id`) REFERENCES `product_specifications` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_product_specification`
--

LOCK TABLES `product_product_specification` WRITE;
/*!40000 ALTER TABLE `product_product_specification` DISABLE KEYS */;
INSERT INTO `product_product_specification` VALUES (5,1),(6,1),(7,1),(8,1),(9,1),(10,1),(10,1),(10,1),(10,1),(10,1),(10,1),(10,1),(10,1),(10,1),(10,1),(10,1),(11,2),(1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(2,1),(2,2),(2,3),(2,4),(2,5),(2,6),(3,1),(3,2),(3,3),(3,4),(3,5),(3,6),(4,1),(4,2),(4,3),(4,4),(4,5),(4,6),(12,2),(12,3),(12,4),(12,5),(12,6);
/*!40000 ALTER TABLE `product_product_specification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_specifications`
--

DROP TABLE IF EXISTS `product_specifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_specifications` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `specification_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_specifications`
--

LOCK TABLES `product_specifications` WRITE;
/*!40000 ALTER TABLE `product_specifications` DISABLE KEYS */;
INSERT INTO `product_specifications` VALUES (1,'Màn hình'),(2,'Camera sau'),(3,'Camera trước'),(4,'Vi xử lý & đồ họa'),(5,'RAM & lưu trữ'),(6,'Tính năng khác'),(7,'Bộ xử lý & Đồ họa'),(8,'Pin & công nghệ sạc'),(9,'Cổng kết nối'),(10,NULL),(11,'Ngày ra mắt'),(12,'Kích thước & Trọng lượng');
/*!40000 ALTER TABLE `product_specifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_variant`
--

DROP TABLE IF EXISTS `product_variant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_variant` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `price_discount` double DEFAULT NULL,
  `price_origin` double DEFAULT NULL,
  `storage` varchar(255) DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtk6wrh1xwqq4vn2pf11mwycgv` (`product_id`),
  CONSTRAINT `FKtk6wrh1xwqq4vn2pf11mwycgv` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_variant`
--

LOCK TABLES `product_variant` WRITE;
/*!40000 ALTER TABLE `product_variant` DISABLE KEYS */;
INSERT INTO `product_variant` VALUES (1,37890000,37990000,'256GB',1),(2,43590000,44490000,'512GB',1),(3,48480000,50990000,'1T',1),(4,62990000,63990000,'2T',1),(5,31590000,34990000,'256GB',2),(6,39490000,40990000,'512GB',2),(7,43990000,46990000,'1TB',2),(8,16790000,19990000,'128GB',3),(9,19890000,22990000,'256GB',3),(10,23990000,28990000,'128GB',4),(11,26290000,31990000,'256GB',4),(12,25590000,29990000,'128GB',5),(13,27990000,32990000,'256GB',5),(14,35390000,38990000,'512GB',5),(15,41790000,45990000,'1T',5),(16,12290000,13990000,'128GB',6),(17,14990000,16990000,'256GB',6),(18,8490000,9990000,'A16 Wifi 128GB',7),(19,11590000,12490000,'A16 Wifi 256GB',7),(20,12990000,13990000,'A16 5G 128GB',7),(21,15690000,16490000,'A16 5G 256GB',7),(22,27590000,28990000,'256GB',8),(23,32390000,34490000,'512GB',8),(24,36990000,45990000,'1TB',8),(25,44990000,59990000,'2TB Nano ',8);
/*!40000 ALTER TABLE `product_variant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `featured_product` bit(1) DEFAULT NULL,
  `url_image_product` varchar(255) DEFAULT NULL,
  `product_code` varchar(50) DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `promotional_product` bit(1) DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `brand_id` bigint DEFAULT NULL,
  `category_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK922x4t23nx64422orei4meb2y` (`product_code`),
  KEY `FKa3a4mpsfdf4d2y6r8ra3sc8mv` (`brand_id`),
  KEY `FKog2rp4qthbtt2lfyhfo32lsw9` (`category_id`),
  CONSTRAINT `FKa3a4mpsfdf4d2y6r8ra3sc8mv` FOREIGN KEY (`brand_id`) REFERENCES `brands` (`id`),
  CONSTRAINT `FKog2rp4qthbtt2lfyhfo32lsw9` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'Trả góp 0% - 0đ phụ thu - 0đ trả trước - kỳ hạn đến 6 tháng',_binary '','uploads/Product/1770041324173_productIphone17.png','DT001','iPhone 17 Pro Max | Chính hãng',_binary '',10,1,1),(2,'Không phí chuyển đổi khi trả góp 0% qua thẻ tín dụng kỳ hạn 3-6 tháng',_binary '','uploads/Product/1770041545072_productIphone16.png','DT002','iPhone 16 Pro Max | Chính hãng VN/A',_binary '',10,1,1),(3,'Không phí chuyển đổi khi trả góp 0% qua thẻ tín dụng kỳ hạn 3-6 tháng',_binary '','uploads/Product/1770041708749_productIphone15Plus.png','DT003','iPhone 15 Plus | Chính hãng VN/A ',_binary '',10,1,1),(4,'Không phí chuyển đổi khi trả góp 0% qua thẻ tín dụng kỳ hạn 3-6 tháng',_binary '','uploads/Product/1770041824827_productIphone15Pro.png','DT004','iPhone 15 Pro  | Chính hãng VN/A ',_binary '',5,1,1),(5,'Không phí chuyển đổi khi trả góp 0% qua thẻ tín dụng kỳ hạn 3-6 tháng',_binary '\0','uploads/Product/1770042019415_productIphone14ProMax.png','DT005','iPhone 14 Pro Max | Chính hãng VN/A ',_binary '',4,1,1),(6,'Không phí chuyển đổi khi trả góp 0% qua thẻ tín dụng kỳ hạn 3-6 tháng',_binary '\0','uploads/Product/1770042121396_productIphone13.png','DT006','iPhone 13  | Chính hãng VN/A ',_binary '',6,1,1),(7,'Không phí chuyển đổi khi trả góp 0% qua thẻ tín dụng',_binary '','uploads/Product/1770385062644_tabletA16.png','TB001','iPad A16 Wifi 128GB 2025 | Chính hãng Apple Việt Nam',_binary '',4,1,2),(8,'Không phí chuyển đổi khi trả góp 0% qua thẻ tín dụng kỳ hạn 3-6 tháng',_binary '','uploads/Product/1770385629153_iPadProM4.png','TB002','iPad Pro M4 11 inch Wifi 256GB | Chính hãng Apple Việt Nam',_binary '',4,1,2),(9,'Tặng ngay gói đặc quyền giá trị 4.5 triệu đồng',_binary '','uploads/Product/1770388031984_productTabletSamsung.png','TB003','Samsung Galaxy Tab S10 Lite Wifi 6GB 128GB',_binary '',5,2,2);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ADMIN'),(2,'USER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `specification_detail`
--

DROP TABLE IF EXISTS `specification_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `specification_detail` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `specification_label` varchar(255) DEFAULT NULL,
  `specification_description_detail` varchar(255) DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  `product_specification_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKaw7flagpdsptmu1stxnugc0kq` (`product_id`),
  KEY `FK8sgl1s8c8u2sv3df5ku7rmn2a` (`product_specification_id`),
  CONSTRAINT `FK8sgl1s8c8u2sv3df5ku7rmn2a` FOREIGN KEY (`product_specification_id`) REFERENCES `product_specifications` (`id`),
  CONSTRAINT `FKaw7flagpdsptmu1stxnugc0kq` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `specification_detail`
--

LOCK TABLES `specification_detail` WRITE;
/*!40000 ALTER TABLE `specification_detail` DISABLE KEYS */;
INSERT INTO `specification_detail` VALUES (1,'Kích thước màn hình','6.9 inches',1,1),(2,'Công nghệ màn hình','Super Retina XDR',1,1),(3,'Độ phân giải màn hình','2868 x 1320 pixels',1,1),(4,'Tần số quét','120Hz',1,1),(5,'Kiểu màn hình','Dynamic Island',1,1),(6,'Camera sau','Chính: 48MP khẩu độ ƒ/1.6 OIS hỗ trợ chụp 24MP hoặc 48MP, Góc Siêu Rộng: 48MP khẩu độ ƒ/2.2 góc nhìn 120°, Telephoto: 48MP khẩu độ ƒ/2.8 OIS zoom quang học lên đến 8x',1,2),(7,'Tính năng camera','Zoom số 40x, Ống kính tùy chỉnh Fusion Main, Kính sapphire, Flash True Tone Thích Ứng, Photonic Engine, Deep Fusion',1,2),(8,'Camera trước','Camera 18MP Center Stage Khẩu độ ƒ/1.9',1,3),(9,'Quay video trước','Quay video 4K Dolby Vision 24/25/30/60 fps, 1080p 25/30/60 fps, Cinematic 4K 30 fps, Quay chậm 1080p 120 fps, QuickTake, Chống rung OIS, HDR, ProRes, Apple Log 2',1,3),(10,'Chipset','Chip A19 Pro',1,4),(11,'GPU','GPU 6 lõi với Neural Accelerator',1,4),(12,'Bộ nhớ trong','256 GB',1,5),(13,'Hệ điều hành','iOS 26',1,6),(14,'Loại CPU','CPU 6 lõi với 2 lõi hiệu năng và 4 lõi tiết kiệm điện',1,7),(15,'Công nghệ sạc','Sạc không dây MagSafe lên đến 25W Sạc không dây Qi2 lên đến 25W',1,8),(16,'Wi-Fi','Wi‑Fi 7 (802.11be) với 2x2 MIMO11',1,9),(17,'Bluetooth','Bluetooth 6',1,9),(18,'Kích thước màn hình','6.9 inches',2,1),(19,'Công nghệ màn hình','Super Retina XDR OLED',2,1),(20,'Độ phân giải màn hình','2868 x 1320 pixels',2,1),(21,'Tần số quét','120Hz',2,1),(22,'Kiểu màn hình','Dynamic Island',2,1),(23,'Camera sau','Camera chính: 48MP, f/1.78, 24mm, 2µm, chống rung quang học dịch chuyển cảm biến thế hệ thứ hai, Focus Pixels 100% Telephoto 2x 12MP: 52 mm, ƒ/1.6 Camera góc siêu rộng: 48MP, 13 mm,ƒ/2.2 và trường ảnh 120°, Hybrid Focus Pixels, ảnh có độ phân giải',2,2),(24,'Camera trước','12MP, ƒ/1.9, Tự động lấy nét theo pha Focus Pixels',2,3),(25,'Chipset','Apple A18 Pro',2,4),(26,'GPU','GPU 6 lõi mới',2,4),(27,'Kích thước','163 x 77,6 x 8,25 mm',2,12),(28,'Trọng lượng','227 gram',2,12),(29,'Kích thước màn hình','6.1 inches',3,1),(30,'Công nghệ màn hình	','Super Retina XDR OLED',3,1),(31,'Độ phân giải màn hình','2556 x 1179 pixels',3,1),(32,'Tần số quét','60Hz',3,1),(33,'Kiểu màn hình','Dynamic Island',3,1),(34,'Tính năng camera','Độ thu phóng kỹ thuật số lên đến 10x, Flash True Tone Photonic Engine HDR thông minh thế hệ 5, Hiệu ứng Chiếu Sáng Chân Dung với sáu chế độ, Phong Cách Nhiếp Ảnh Tự động chống rung hình ảnh',3,2),(35,'Camera trước','12MP, ƒ/1.9',3,3),(36,'Chipset','Apple A16 Bionic 6 nhân',3,4),(37,'GPU','GPU 5 lõi',3,4),(38,'Kích thước','147.6 x 71.6 x 7.80 mm',3,12),(39,'Trọng lượng','171g',3,12),(40,'Kích thước màn hình','6.1 inches',4,1),(41,'Công nghệ màn hình	','Super Retina XDR OLED',4,1),(42,'Độ phân giải màn hình','2556 x 1179 pixels',4,1),(43,'Tần số quét','120Hz',4,1),(44,'Kiểu màn hình','Dynamic Island',4,1),(45,'Camera sau','Camera chính: 48MP, 24 mm, ƒ/1.78, Camera góc siêu rộng: 12 MP, 13 mm, ƒ/2.2 Camera Tele 3x: 12 MP, 77 mm, ƒ/2.8',4,2),(46,'Tính năng camera','Flash True Tone Thích Ứng Photonic Engine Deep Fusion HDR thông minh thế hệ 5',4,2),(47,'Camera trước','12MP, ƒ/1.9',4,3),(48,'Quay video trước','4K@24/25/30/60 fps HD 1080p@25/30/60 fps',4,3),(49,'Chipset','Apple A17 Pro 6 nhân',4,4),(50,'GPU','GPU 6 nhân mới',4,4),(51,'Kích thước','146,6 x 70,6 x 8,25 mm',4,12),(52,'Trọng lượng','187 g',4,12),(53,'Kích thước màn hình','6.7 inches',5,1),(54,'Công nghệ màn hình','Super Retina XDR OLED',5,1),(55,'Độ phân giải màn hình','2796 x 1290-pixel',5,1),(56,'Tần số quét','120Hz',5,1),(57,'Kiểu màn hình','Dynamic Island',5,1),(58,'Camera sau','Camera chính: 48 MP, f/1.8, 24mm, 1.22µm, PDAF, OIS Camera góc siêu rộng: 12 MP, f/2.2, 13mm, 120˚, 1.4µm, PDAF Camera tele: 12 MP, f/2.8, 77mm (telephoto), PDAF, OIS, 3x optical zoom Cảm biến độ sâu TOF 3D LiDAR',5,2),(59,'Camera trước','Camera selfie: 12 MP, f/1.9, 23mm, 1/3.6\", PDAF',5,3),(60,'Chipset','Apple A16 Bionic 6-core',5,4),(61,'GPU','Apple GPU (5-core graphics)',5,4),(62,'Kích thước','160.7 x 77.6 x 7.9 mm',5,12),(63,'Trọng lượng','240 gr',5,12),(64,'Kích thước màn hình','6.1 inches',6,1),(65,'Công nghệ màn hình','Super Retina XDR OLED',6,1),(66,'Độ phân giải màn hình','2532 x 1170 pixels',6,1),(67,'Tần số quét','60Hz',6,1),(68,'Kiểu màn hình','Tai thỏ',6,1),(69,'Camera sau','Camera góc rộng: 12MP, f/1.6 Camera góc siêu rộng: 12MP, ƒ/2.4',6,2),(70,'Camera trước','12MP, f/2.2',6,3),(71,'Chipset','Apple A15',6,4),(72,'GPU','Apple GPU 4 nhân',6,4),(73,'Kích thước','146,7 x 71,5 x 7,65mm',6,12),(74,'Trọng lượng','173g',6,12);
/*!40000 ALTER TABLE `specification_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Nam@gmail.com','Trần Hồng Nam','$2a$10$PC4WOKt8L90BVsO3TCZEHOsxnXAWd9GcvTWCMvSw4rVoMDkMzhU56','0912345678');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_roles` (
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  KEY `FKj6m8fwv7oqv74fcehir1a9ffy` (`role_id`),
  KEY `FK2o0jvgh89lemvvo17cbqvdxaa` (`user_id`),
  CONSTRAINT `FK2o0jvgh89lemvvo17cbqvdxaa` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKj6m8fwv7oqv74fcehir1a9ffy` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_roles`
--

LOCK TABLES `users_roles` WRITE;
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
INSERT INTO `users_roles` VALUES (1,2);
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-02-11 19:09:33
