# Web-Portal
Web Portal

Spring Boot web application for User management. 
Built using Spring Boot, Spring Security, MySQL Database, FreeMarker (Java Template Engine to generate HTML web pages), CSS (Bootstrap).

Functionalities Implemented:
- User Log In ( Spring Security )
- User Register
- User Edit Details 
- User Change Password (BCrypt Encryption)
- User Upload Profile picture
- Captcha Verification on User Register

Test User details: 
- email: marko@mail.com
- password: passw0rd!

Notes:
- PROFILE PICTURE UPLOAD: Re-start Spring application after uploading the image as resources are bundled all together by the IDE before running the system. Otherwise, please navigate to /resources/static/photos and use some sample images I have included. Although this is an IDE problem, in a real-world scenario, these images would be saved somewhere else too.

