# الخطوة 1: بيئة بناء المشروع باستخدام مايفن وجافا 8
FROM maven:3.8.6-openjdk-8 AS build
WORKDIR /app

# نسخ ملفات الإعدادات والتحميل المسبق للمكتبات
COPY pom.xml .
RUN mvn dependency:go-offline -B

# نسخ الكود المصدري وبناء ملف الـ JAR
COPY src ./src
RUN mvn clean package -DskipTests

# الخطوة 2: بيئة التشغيل الخفيفة والمستقرة
FROM amazoncorretto:8-alpine
WORKDIR /app

# جلب ملف الـ JAR الذي تم بناؤه في الخطوة الأولى
COPY --from=build /app/target/*.jar app.jar

# تشغيل النظام
ENTRYPOINT ["java", "-jar", "app.jar"]