
![](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white) ![](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white) ![](https://img.shields.io/badge/Microsoft%20SQL%20Server-CC2927?style=for-the-badge&logo=microsoft%20sql%20server&logoColor=white) ![](https://img.shields.io/badge/Docker-2CA5E0?style=for-the-badge&logo=docker&logoColor=white`)

# Boyner Case Study

[Projenin kaynak java classları](/src/main/java/com/Boyner/BoynerCase/)

[Projeyi localinizde çalıştırmanız için gerekli docker-compose dosyası](/docker-compose.yml)

[Ubuntu için docker kurulumu](https://docs.docker.com/engine/install/ubuntu/)

Docker imajlarını ayağa kaldırma⇂
```terminal
# Cleaning up Docker images
docker rm -f $(docker ps -a -q)
docker volume rm $(docker volume ls -q)

# Running all images (While in the project folder)
docker-compose up
```
Docker imajları ayağa kaktıktan sonra oluşacak tablo şeması⇂

![Screenshot_2022-06-01_13-19-16](https://user-images.githubusercontent.com/54184905/171382747-2d9e3f51-c3c4-47e3-9572-6379907c2b88.png)

## API kullanımı

Docker imajlarını ayağa kaldırdıktan sonra [http://localhost:8080/api/](http://localhost:8080/api/) bağlantısı ile API yi localinizde kullanabilirsiniz, veya [http://52.91.218.62:8080/api/](http://52.91.218.62:8080/api/) bağlantısı ile AWS EC2 üzerinde deploy ettiğim canlı API yi kullanabilirsiniz.

* **ProductCatalog**

**Post :⇂**

**tbl_category_attributes** tablosuna yeni item ekleme⇂
[http://localhost:8080/api/createProductCategoryAttributes](http://localhost:8080/api/createProductCategoryAttributes)
```json
{
    "brand":"brand",
    "color":"red",
    "gender":"male",
    "size":"xl"
}
```
![Screenshot_2022-06-01_13-46-45](https://user-images.githubusercontent.com/54184905/171387417-f2436d81-3768-4abb-a6c7-b43ec64c3053.png)

**tbl_product_category** tablosuna yeni item ekleme⇂
[http://localhost:8080/api/createProductCategory](http://localhost:8080/api/createProductCategory)
```json
{
    "name":"TshortCategory",
    "category_attributes":1
}
```
![Screenshot_2022-06-01_13-50-32](https://user-images.githubusercontent.com/54184905/171388161-7250fb51-efa1-416b-94f2-61599f3bf20e.png)

**Get :⇂**

**tbl_product_category** tablosunda isim ile kategori arama⇂
[http://localhost:8080/api/PrCategoryName](http://localhost:8080/api/PrCategoryName)
```
http://localhost:8080/api/PrCategoryName?name=TshortCategory
```
![Screenshot_2022-06-01_13-58-34](https://user-images.githubusercontent.com/54184905/171389367-072b4afa-18a6-4a8e-ae1f-0eea514a51c8.png)

**Put :⇂**

**tbl_product_category** tablosunda id ile güncelleme yapma⇂
[http://localhost:8080/api/updateProductCategory](http://localhost:8080/api/updateProductCategory)
```json
{
    "id":1,
    "name":"TshortCategory NEW",
    "category_attributes":1
}
```
![Screenshot_2022-06-01_14-02-10](https://user-images.githubusercontent.com/54184905/171390185-b07d78f7-f072-4309-9ee6-d10f0bcfeec8.png)

**Delete :⇂**

**tbl_product_category** tablosunda id ile bağlı tüm tablolara soft delete yapma⇂
[http://localhost:8080/api/deleteProductCategory](http://localhost:8080/api/deleteProductCategory)
```
http://localhost:8080/api/deleteProductCategory?id=1
```
![Screenshot_2022-06-01_15-01-34](https://user-images.githubusercontent.com/54184905/171399798-e4a78f55-4182-4a22-8cd2-961db4612cec.png)

* **Product**

**Post :⇂**

**tbl_product_attributes** tablosuna yeni item ekleme⇂
[http://localhost:8080/api/createProductAttributes](http://localhost:8080/api/createProductAttributes)
```json
{
    "brand":"brandProduct",
    "color":"blue",
    "gender":"Unisex",
    "size":"m"
}
```
![Screenshot_2022-06-01_14-26-58](https://user-images.githubusercontent.com/54184905/171393992-ef456253-0151-4c25-b7e6-1e7ed1239337.png)

**tbl_product** tablosuna yeni item ekleme⇂
[http://localhost:8080/api/createProduct](http://localhost:8080/api/createProduct)
```json
{
    "name":"Boyner Tshort",
    "price":99.99,
    "product_attributes":2,
    "product_catalog_id":1
}
```
![Screenshot_2022-06-01_14-30-00](https://user-images.githubusercontent.com/54184905/171394600-cbe47a85-0c62-4d40-adf9-9418e368f016.png)

**Get :⇂**

**tbl_product** tablosunda name ile item arama⇂
[http://localhost:8080/api/productName](http://localhost:8080/api/productName)
```
http://localhost:8080/api/productName?name=Boyner Tshort
```
![Screenshot_2022-06-01_14-36-43](https://user-images.githubusercontent.com/54184905/171395606-75d6be81-82ba-4094-a8d6-6b76a091521e.png)

**tbl_product** tablosunda CategoryName ile item arama⇂
[http://localhost:8080/api/productCategoryName](http://localhost:8080/api/productCategoryName)
```
http://localhost:8080/api/productCategoryName?name=TshortCategory NEW
```
![Screenshot_2022-06-01_14-38-22](https://user-images.githubusercontent.com/54184905/171396014-e22e6196-69e5-4507-ade3-1b129e433693.png)

**tbl_product** tablosunda PriceRange ile item arama⇂
[http://localhost:8080/api/productPriceRange](http://localhost:8080/api/productPriceRange)
```
http://localhost:8080/api/productPriceRange?start=98.8&finish=100
```
![Screenshot_2022-06-01_14-41-13](https://user-images.githubusercontent.com/54184905/171396423-10acefd8-8ab3-423d-aa0a-e1cfdc5893d0.png)

**Put :⇂**

**tbl_product** tablosunda id ile item güncelleme⇂
[http://localhost:8080/api/updateProduct](http://localhost:8080/api/updateProduct)
```json
{
    "id":1,
    "name":"Mavi Tshort",
    "price":250.20,
    "product_attributes":1,
    "product_catalog_id":1
}
```
![Screenshot_2022-06-01_14-45-34](https://user-images.githubusercontent.com/54184905/171397179-1bf948a0-13d0-4405-ad16-bfacdf03611b.png)

**Delete :⇂**

**tbl_product** tablosunda id ile item silme⇂
[http://localhost:8080/api/deleteProduct](http://localhost:8080/api/deleteProduct)
```
http://localhost:8080/api/deleteProduct?id=1
```
![Screenshot_2022-06-01_14-47-56](https://user-images.githubusercontent.com/54184905/171397648-555094a6-4592-41de-a756-77653752e871.png)
