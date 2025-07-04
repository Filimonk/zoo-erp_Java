# Отчет по выполнению задания "Банковская система"

## Описание реализованной функциональности

В рамках проекта была разработана консольная банковская система. Базовая функциональность охватывает управление банковскими счетами, категориями и операциями.

Для каждой сущности предусмотрена полная работа с данными: создание, вывод и удаление объектов (удаление реализовано на уровне сервисов, но не доступно из пользовательского интерфейса — оставлено как внутреннее API программиста).

В пользовательский интерфейс были вынесены следующие действия, доступные пользователю через меню:

1. Создать банковский счёт
2. Показать все счета
3. Создать категорию
4. Показать все категории
5. Создать операцию
6. Показать все операции
7. Завершить работу программы

При выборе действия "создать" пользователь пошагово вводит необходимые параметры. После ввода данные передаются в `Gateway`, который выполняет бизнес-валидацию:
- проверяется наличие связанных сущностей (например, существование банковского счёта или категории),
- корректность совпадения типа операции и типа категории.

После успешной логической проверки данные передаются в сервисный слой. Там осуществляется создание новых объектов с дополнительной проверкой их фактической корректности.

При выборе команд на отображение данных — запрос поступает через `Gateway` в сервис, далее полученный список объектов поэлементно форматируется с помощью специально созданного `Formatter`, и выводится пользователю в консоль.

Команда завершения корректно останавливает выполнение программы.

## Использование принципов SOLID

### Single Responsibility Principle (SRP)

В проекте каждая сущность выполняет строго определённую функцию. Например:
- Фабрики (`OperationFactory`, `CategoryFactory`, `BankAccountFactory`) отвечают только за создание объектов.
- `BankFormatter` — исключительно за форматирование объектов для вывода.
- Сервисы — за хранение и управление коллекциями объектов.
- `Gateway` — за координацию бизнес-логики.

### Dependency Inversion Principle (DIP)

Внедрение зависимостей организовано с помощью Spring DI-контейнера. Компоненты зависят от абстракций (интерфейсов), а конкретные реализации передаются через конфигурацию, что упрощает тестирование и сопровождение.

## Использование принципов GRASP

### Controller

Класс `BankingGateway` реализует поведенческую роль контроллера, принимая и обрабатывая все пользовательские команды, инкапсулируя бизнес-логику системы.

### Creator

Создание экземпляров сущностей вынесено в отдельные фабрики (`OperationFactory`, `CategoryFactory`, `BankAccountFactory`), что позволяет централизовать процесс инициализации объектов.

## Использование паттернов GoF

### Factory Method

Паттерн применён через собственные фабрики, создающие объекты доменной модели (категории, операции, счета).

### Facade

Класс `BankingGateway` выполняет роль фасада, предоставляя упрощённый и единый интерфейс для взаимодействия между пользовательским интерфейсом и бизнес-логикой.

## Инструкция по запуску

1. Перейти в директорию проекта.
2. Выполнить команду:
```bash
gradle run
```
3. Следовать инструкциям в консоли.

## Соответствие критериям оценивания

### Критерии оценки:

- Полная реализация основных требований к функциональности:
  Выполнено — +2 балла

- Реализация паттернов (из предложенных или выбранных самостоятельно):
  Выполнено 2 паттерна (Factory Method, Facade) — +1 балл

- Соблюдение принципов SOLID и GRASP:
  Выполнено — +2 балла

- Покрытие тестами более 65% кода:
  Не выполнено — 0 баллов

- Использование DI-контейнера:
  Выполнено — +1 балл

- Очная защита проекта:
  Не выполнено — 0 баллов

