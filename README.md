# GPX Management
## Introduction
This project is to manage the gpx files that are uploaded by users.
It includes features:
- Upload GPX Files and store the mandatory information.
- Get the recent uploaded GPX files.
- Get detail of the specific uploaded GPX file.

## Build with
- Java 14
- SpringBoot (web, data jpa)
- jpx library 2.0.0
- Maven 3.63
- JUnit 4
- H2 Database

## Installing
~~~~
    git clone https://github.com/phandinhthe/gpx.git
~~~~
Go to folder gpx and run command:
~~~~
    mvn clean install
~~~~

## Features
###1. Upload GPX file
Sequence diagram
![alt_tag](https://user-images.githubusercontent.com/14273018/90172703-979e7580-ddcd-11ea-8a77-346da5a92185.png)
![alt_tag](https://user-images.githubusercontent.com/14273018/90173004-f95edf80-ddcd-11ea-820f-783e0ae71586.png)

  - Request:
~~~
    curl --location --request POST 'http://localhost:8081/gpx/api/v1/gpx/upload' \
    --form 'file=@/Users/phandinhthe/Downloads/aaa.gpx'
~~~

  - Response:
~~~
    {"id":1}
~~~

###2. Get recent files uploaded by users
![alt_tag](https://user-images.githubusercontent.com/14273018/90174899-deda3580-ddd0-11ea-94ba-ace137c2fa2c.png)
- Request:
* This api will get the latest GPX files uploaded with limit and paged by param *limit*, *page*
 * * default value of param *page* is 0
 * * default value of param *limit* is 10
~~~~
    curl --location --request GET 'http://localhost:8081/gpx/api/v1/gpx?page=0&limit=5'
~~~~

* * You can call with default values like:
    
~~~~
    curl --location --request GET 'http://localhost:8081/gpx/api/v1/gpx'
~~~~

###3. Get detail of uploaded file by specific id
![alt_tag](https://user-images.githubusercontent.com/14273018/90175638-e6e6a500-ddd1-11ea-8ce4-8e26a61985fa.png)
![alt_tag](https://user-images.githubusercontent.com/14273018/90175643-e817d200-ddd1-11ea-9c25-172ffcb3b200.png)
- Request:
* * This api will get detail of the specific GPX uploaded in 'GPX Upload api'. In this case, the request will get the uploaded file having id '1'
~~~~
    curl --location --request GET 'http://localhost:8081/gpx/api/v1/gpx/1'
~~~~
