# Название проекта
MY FRAMEWORK (ШАБЛОН ПРОЕКТА ПО АВТОМАТИЗИРОВАННОМУ ТЕСТИРОВАНИЮ)

# Содержание
1. [Описание проекта](#описание-проекта)
2. [Установка и запуск тестов](#установка-и-запуск-тестов)
3. [Структура проекта](#структура-проекта)
4. [Запуск через CI/CD](#запуск-через-CI/CD)
5. [Отчеты](#отчеты)
6. [Дополнительные ресурсы](#дополнительные_ресурсы)

# Описание проекта
Этот проект представляет собой фреймворк для автоматизированного тестирования
Фреймворк поддерживает тестирование через API и UI.
Включает в себя работу с базами данных, тестовыми данными, а также позволяет легко управлять настройками проекта.

# Установка и запуск тестов
### Клонирование проекта на локальную машину
`git clone https://github.com/Bgalina86/inno_itog_project.git`
### Установка зависимостей
`mvn clean install`
### Настройка конфигурационных файлов
Откройте файл `src/main/resources/conf.properties.`
Обновите необходимые параметры, такие как URL для тестов, данные для подключения к базе данных и т.д.
### Запуск всех тестов с использованием окружения по умолчанию
По умолчанию запускаются тесты с conf.properties
`mvn clean test`
# Структура проекта
- **`.github/workflows/maven.yaml`**:Конфигурационный файл для автоматического выполнения процессов Continuous Integration (CI) с использованием GitHub Actions
- **`src/test/java/api_inno_itog_project`**: Папка с директориями и тестовыми классами по тестированию API
   - **`src/test/java/api_inno_itog_project/constClass`**: Содержит класс констант со статусной моделью ответов с кодом
   - **`src/test/java/api_inno_itog_project/x_clients/ext`**: Находятся классы для настройки соединения с базой данных и SQL запросы
   - **`src/test/java/api_inno_itog_project/x_clients/helper`**: Содержит вспомогательные классы с основными методами
   - **`src/test/java/api_inno_itog_project/x_clients/model`**: В этой папке находятся модели данных, используемые в запросах и для представления различных сущностей.
- **`src/test/java/helper`**: Содержатся класс для настройки окружения, запуска тестов.
- **`src/test/java/ui_inno_itog_project`**: Папка с директориями и тестовым классом по тестированию UI
   - **`src/test/java/ui_inno_itog_project/page_object`**: Содержит классы, реализующие шаблон PageObject для работы с UI тестами
      - **`src/test/java/ui_inno_itog_project/page_object/ext`**: Находятся вспомогательные классы  
      - **`src/test/java/ui_inno_itog_project/page_object/pom`**: Содержит организационные папки для PageObject 
         - **`src/test/java/ui_inno_itog_project/page_object/pom/elements`**: Содержит постраничные классы с локаторами XPath
         - **`src/test/java/ui_inno_itog_project/page_object/pom/pages`**: Содержит постраничные классы с методами
   - **`src/test/java/ui_inno_itog_project/BaseTest.java`**: Организационный класс
- **`src/test/resources/`**: В этой папке находятся файлы с настройками для тестов, такими как конфигурации окружений, а также отчеты о тестировании.
- **`.gitignore`**: Файл для указания тех файлов и папок, которые не должны попадать в репозиторий.
- **`pom.xml`**: Основной файл для управления зависимостями Maven и настройки сборки проекта.

# Запуск через CI/CD
Для автоматического запуска тестов и генерации отчетов используется CI/CD процесс,
настроенный с помощью `GitHub Actions`. (Для успешного построения отчета на GitHub в Allure нужно загрузить директории allure-results и allure-report)
1. Конфигурационный файл для автоматического выполнения процессов Continuous Integration (CI) с использованием GitHub Actions лежит в папке `.github/workflows/ci.yaml`.
2. Запуск тестов:
   При каждом `push` в ветку `main` автоматически запускаются UI и API тесты.
3. После завершения тестов генерируется отчет Allure.
4. Отчет Allure автоматически публикуется на GitHub Pages по ссылке https://bgalina86.github.io/inno_itog_project/

# Отчеты
Для отображения Allure-отчетов в html формате необходимо установить Allure CLI 
Для быстрой генерации отчетов `allure serve`
Для генерации отчетов в проекте необходимо выполнить команду `allure generate`  
Для открытия отчета в html необходимо выполнить команду `allure open`

### Отчёт по прогону в Allure
### Suites
![Suites](./src/test/resources/img/Allure_report_Suites.jpg)

### Graphs
![Graphs](./src/test/resources/img/Allure_report_Graphs.jpg)

### Behaviors
![Behaviors](./src/test/resources/img/Allure_report_Behaviors.jpg)

### Test body
![Allure report Test body](./src/test/resources/img/Allure_report_Test_body.jpg)

### Failed
![Allure report Failed](./src/test/resources/img/Allure_report_Failed.jpg)

### Создание отчета с учетом предыдущих запусков:
1. Запускаем создание отчета `allure generate`. Отчет будет сохранен в директорию `allure-results`
2. Из `allure-report` в `allure-results` тащим папку `history`
3. Папку `allure-report` удалить
4. `allure generate`
5. `allure open`

### Trend
![Allure report Trend](./src/test/resources/img/Allure_report_Trend.jpg)

# Дополнительные ресурсы
- [Allure Framework: Официальная документация](https://allure.qatools.ru/)
- [Мавен - Руководство пользователя](https://maven.apache.org/guides/)


