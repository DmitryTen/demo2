Тестовое задание 
	
2) Реализовать API с использованием которое позволит удалять данные из реляционной БД  таблиц объемом 10 млн строк и более. Ключ, по которому производится удаление данных, столбец DateTime (удаляем старые данные). Работа системы не должна приводить к длительным блокировкам данных. АPI может вызываться несколькими внешними процессами одновременно. (1 процесс удаляет данные в 1 таблице). Необходимо обеспечить логирование работы API.


Для тестирования приложения необходимо подключение к постгрес БД.
дефолтные значения:

    url: jdbc:postgresql://localhost:5432/demo
    username: postgres
    password: postgres

Для накатывания данных на вновь-созданную БД, используется liquibase. Необходимо выполнить:

    mvn -f liquibase/ clean verify -P liquibase


Реализовано REST-api, 

    demo.project.vimpelcom.controllers.DemoController 
    

Пример тестовых запросов:

    curl -XDELETE http://localhost:8080/api/table_a/old_records/2010-01-01T23:59:59
    {"response_date_time":"2021-04-26T16:01:05.798872","deleted_ids":[133,134,135,136,137,138,139,140,141,142],"deleted_cnt":10}
    
    curl -XDELETE http://localhost:8080/api/table_b/old_records/2010-01-01T23:59:59
    {"response_date_time":"2021-04-26T16:01:25.93602","deleted_ids":["9970c28e-de24-e991-ad56-6f9f264d88b1","a55bc41e-ee35-b1d6-9c8c-ffec4a2077b2","f3124b5c-bfc7-d976-ff08-bd9604520472","c767d2b9-60df-14d3-5695-d0e04db14053","ba12937e-0a4e-a8f5-50b9-661b4c1b4e5c","e282c8f5-fc98-63b1-69a4-60e8310ae5fc","0e098e36-7562-6eab-4236-913269984c67","69f7b717-d282-b5b0-0ebb-3a3e147dfc5e","75f2674a-f593-8b45-7be2-30ae9213be51","a2c92333-1d2a-3ac6-c9ed-cee2bb061974"],"deleted_cnt":10}
    
    curl -XDELETE http://localhost:8080/api/table_c/old_records/2010-01-01T23:59:59
    {"response_date_time":"2021-04-26T16:22:34.175299","deleted_ids":[130,131,132,133,134,135,136,137,138,139],"deleted_cnt":10}
    
