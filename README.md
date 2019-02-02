### Done

- [x] run tests in parallel mode;

```mvn clean test```

- [x] ability to run tests for different browsers/mobile devices by configuring;
- [x] ability to run tests for different environments(urls) by configuring/by command-line.

```mvn clean test -Denv=prod -Dbrowser=firefox``` 

### Generate Allure report 

```mvn allure:report```

```mvn allure:serve```

### Video how does it looks like

- parallel run
- on diff browser(firefox) + env(prod)

