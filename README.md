Тестовое задание 
	
2) Реализовать API с использованием которое позволит удалять данные из реляционной БД  таблиц объемом 10 млн строк и более. Ключ, по которому производится удаление данных, столбец DateTime (удаляем старые данные). Работа системы не должна приводить к длительным блокировкам данных. АPI может вызываться несколькими внешними процессами одновременно. (1 процесс удаляет данные в 1 таблице). Необходимо обеспечить логирование работы API.


Для тестирования приложения необходимо подключение к постгрес БД.
дефолтные значения:

    url: jdbc:postgresql://localhost:5432/demo
    username: postgres
    password: postgres

Для накатывания данных на вновь-созданную БД, используется liquibase. Необходимо выполнить:

    mvn -f liquibase/ clean verify -P liquibase
    
Обращаю внимание, liquibase генерирует порядка 30млн тестовых значений, поэтому заливка может занять минут 10


Реализовано REST-api, 

    demo.project.vimpelcom.controllers.DemoController 
    
Апи состоит из 3-ех типов запросов:
1. Синхронное удаление по дате (при 10 млн запросов, реквест может висеть длительное время)
2. Асинхронное удаление по дате (в параметр запроса передается callbackUrl, на который придет ответ по выполнению)
3. Создание записей, для проверки что сервис не висит и успешно работает даже в процессе удаления записей.    

Пример тестовых запросов (синхронные):

    curl -XDELETE http://localhost:8080/api/table_a/old_records/2005-01-01T23:59:59
    {"response_date_time":"2021-04-26T16:01:05.798872","deleted_ids":[133,134,135,136,137,138,139,140,141,142],"deleted_cnt":10}
    
    curl -XDELETE http://localhost:8080/api/table_b/old_records/2005-01-01T23:59:59
    {"response_date_time":"2021-04-26T16:01:25.93602","deleted_ids":[133,134,135,136,137,138,139,140,141,142],"deleted_cnt":10}
    
    curl -XDELETE http://localhost:8080/api/table_c/old_records/2005-01-01T23:59:59
    {"response_date_time":"2021-04-26T16:22:34.175299","deleted_ids":[130,131,132,133,134,135,136,137,138,139],"deleted_cnt":10}
    
Пример тестовых запросов (асинхронные, callbackUrl=http://localhost:8080/mock/callbackMock (в сервисе предусмотрен специальный 
мок контроллер для получения коллбеков)):

    curl -XDELETE http://localhost:8080/api/table_a/old_records/async/2005-01-01T23:59:59?callbackUrl=http%3A%2F%2Flocalhost%3A8080%2Fmock%2FcallbackMock
    {"response_date_time":"2021-04-26T20:37:37.089625"}
    
    curl -XDELETE http://localhost:8080/api/table_b/old_records/async/2005-01-01T23:59:59?callbackUrl=http%3A%2F%2Flocalhost%3A8080%2Fmock%2FcallbackMock
    {"response_date_time":"2021-04-26T20:37:37.089625"}

    curl -XDELETE http://localhost:8080/api/table_c/old_records/async/2005-01-01T23:59:59?callbackUrl=http%3A%2F%2Flocalhost%3A8080%2Fmock%2FcallbackMock
    {"response_date_time":"2021-04-26T20:37:37.089625"}
    
Пример тестовых запросов на создание записей:

    curl -XPOST http://localhost:8080/api/table_a -H 'Content-Type: application/json' -d '{"name"="test"}'
    {"response_date_time":"2021-04-26T20:41:13.080084","created_id":4261045}
    
    curl -XPOST http://localhost:8080/api/table_b -H 'Content-Type: application/json' -d '{"name"="test"}'
    {"response_date_time":"2021-04-26T20:41:13.080084","created_id":4261045}
    
    curl -XPOST http://localhost:8080/api/table_c -H 'Content-Type: application/json' -d '{"name"="test"}'
    {"response_date_time":"2021-04-26T20:41:13.080084","created_id":4261045}