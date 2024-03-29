Для запуска приложения
1) Необходимо запустить отдельно модуль SupplierService и отдельно модуль ConsumerService.

    Протестировать можно, запустив документацию swagger-openapi.

    Документация openapi:
    
    http://localhost:8080/swagger-ui/index.html#/  SupplierService
    
    http://localhost:8081/swagger-ui/index.html#/  ConsumerService

2) Другой способ - запустить docker-compose.yml
-Сформировать jar файлы для SupplierService и ConsumerService
- Важно! В перед формированием jar файлов:
- в модуле ConsumerService в файлах CategoryServiceImpl и ProductServiceImpl надо изменит путь к модулю SupplierService

- static String categoryUrl = "http://localhost:8080/api/v1/categories" заменить на static String categoryUrl = "http://supplier:8080/api/v1/categories";

- static String productUrl = "http://localhost:8080/api/v1/products" заменить на static String productUrl = "http://supplier:8080/api/v1/products"

    Протестировать можно, так же, запустив документацию swagger-openapi.

Коммит 1

Тема: Разработка микросервисов с использованием Spring JPA, Spring Boot и RestTemplate

Цель:

Создать два микросервиса для управления данными с использованием Spring JPA, Spring Boot и RestTemplate. 

Один микросервис будет являться источником данных (Supplier Service), предоставляющим информацию о продуктах.

Второй микросервис будет потребителем этих данных (Consumer Service), обрабатывающим информацию о продуктах.

Микросервис-источник данных (Supplier Service):

Создать микросервис, используя Spring Boot.

Использовать Spring JPA для работы с базой данных.

Определить сущности "Product" и "Category" со следующими полями:

Product: id, name, description, price, categoryId.

Category: id, name.

Реализовать связь между продуктами и категориями (ManyToOne, OneToMany).

Реализовать API для управления данными о продуктах и категориях с помощью следующих методов:

POST /products: Создать новый продукт.

GET /products: Получить список всех продуктов.

GET /products/{id}: Получить информацию о продукте по его идентификатору.

PUT /products/{id}: Обновить информацию о продукте.

DELETE /products/{id}: Удалить продукт по его идентификатору.

POST /categories: Создать новую категорию.

GET /categories: Получить список всех категорий.

GET /categories/{id}: Получить информацию о категории по ее идентификатору.

PUT /categories/{id}: Обновить информацию о категории.

DELETE /categories/{id}: Удалить категорию по ее идентификатору.

Добавить возможность загрузки тестовых данных при запуске приложения.

Микросервис-потребитель данных (Consumer Service):

Создать микросервис, используя Spring Boot.

Использовать RestTemplate для взаимодействия с API микросервиса-источника данных.

Реализовать следующую функциональность:

Получение списка всех продуктов и категорий и вывод информации о них.

Возможность добавления нового продукта с указанием категории.

Возможность обновления информации о продукте.

Возможность удаления продукта.

Реализовать возможность фильтрации продуктов по различным критериям, таким как цена, категория и т. д.

Добавить функциональность для поиска продуктов по их названию или описанию.

Реализовать пагинацию для списка продуктов.

Реализовать валидацию данных перед отправкой запросов на сервер.

Обработать возможные ошибки при взаимодействии с API микросервиса-источника данных.

Коммит 2

Дополнительное задание (по желанию):

Добавить возможность оценки продуктов и оставления отзывов о них.

Использовать Docker для контейнеризации обоих микросервисов и развертывания их в контейнерах.

Дополнительно:

Добавил документацию openapi

http://localhost:8080/swagger-ui/index.html#/

http://localhost:8081/swagger-ui/index.html#/

Добавил несколько тестов

Исправил выявленные ошибки

