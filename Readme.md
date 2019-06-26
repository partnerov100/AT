Maven command line:
clean test -Dserver=${server} -DrunType=${runType} -pl ui
runType = local/server (тип запуска локально/серверный селеноид)
server = dev/stage/prod (создать необходимый .properties файл)
-pl ui - запуск только ui тестов

Selenoid:
http://192.168.104.151:8081 - SelenoidUI, здесь можно смотреть процесс выполнения тестов
http://192.168.104.151:4444/status - browser versions

Selenoid base commands:
"./cm selenoid update --last-versions 2" - установит(обновит) две последние версии всех браузеров
./cm selenoid status
./cm selenoid-ui status
./cm selenoid start
./cm selenoid stop
./cm selenoid-ui start -port 8081

Локальный запуск через TestNG:
Дефолтный runType указан в Config.class (runType=local), в этом случае тесты запустятся локально
Дефолтный server указан в Props.class (server=stage), можно менять на необходимый контур
