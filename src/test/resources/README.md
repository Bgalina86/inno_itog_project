# MY FRAMEWORK
(ШАБЛОН ПРОЕКТА ПО АВТОМАТИЗИРОВАННОМУ ТЕСТИРОВАНИЮ)

## Содержит
* UI - тесты
* API - тесты
* Настройка тестов с Allure

## Описание framework
1. UI - тесты
2. API - тесты
3. Команды для запуска тестов:
Запуск по средствам Maven
 - в терминале набрать команду 
    **mvn test**
Запуск с помощью правой кнопки мыши (ПКМ)
 - ПКМ по наименованию директории и выбрать **Run 'All Tests'**
4. Команды для построения отчётности в Allure
 - **allure serve** - быстрое построение отчетов без сбора статистики по предыдущим запускам
Создание отчета с учетом предыдущих запусков:
   1. Запускаем создание отчета `allure generate`. Отчет будет сохранен в директорию `allure-results`
   2. Из `allure-report` в `allure-results` тащим папку `history`
   3. Папку `allure-report` удалить
   4. `allure generate`
   5. `allure open`
 
## Отчёт по прогону в Allure
### Suites

![Suites](./img/Allure_report_Suites.jpg)

### Graphs
![Graphs](./img/Allure_report_Graphs.jpg)

### Behaviors
![Behaviors](./img/Allure_report_Behaviors.jpg)

### Test body
![Allure report Test body](./img/Allure_report_Test_body.jpg)

### Failed
![Allure report Failed](./img/Allure_report_Failed.jpg)

### Trend
![Allure report Trend](./img/Allure_report_Trend.jpg)