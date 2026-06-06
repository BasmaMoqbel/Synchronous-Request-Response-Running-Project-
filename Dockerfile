# الخطوة 1: استخدام نسخة جافا 8 خفيفة ومستقرة لتشغيل النظام
FROM amazoncorretto:8-alpine

# الخطوة 2: تحديد مكان العمل داخل الحاوية
WORKDIR /app

# الخطوة 3: نسخ ملف الـ jar المولد من مشروعك إلى داخل الحاوية
COPY target/*.jar app.jar

# الخطوة 4: فتح المنفذ الذي برمجنا الخدمة عليه
EXPOSE 8085

# الخطوة 5: الأمر النهائي لتشغيل الـ Microservice
ENTRYPOINT ["java", "-jar", "app.jar"]