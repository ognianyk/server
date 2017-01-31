/**
 * Created by pavelognianyk on 10/24/16.
 */
angular.module('krotApp', [])
    .controller('MainTableController', function ($scope, $http) {
        var stompClient = null;
        var main = this
        main.dataFromRaspberry = [];
        main.heatingStatus = true;


        main.heatingDurationOptions = [
            {value: 900, title: "15 minutes"},
            {value: 1800, title: "30 minutes"},
            {value: 3600, title: "1 hour"},
            {value: 5400, title: "1,5 hour"},
            {value: 10800, title: "3 hours"},
            {value: 18000, title: "5 hours"},
            {value: 28800, title: "8 hours"}
        ];

        main.heatingPeriodOptions = [
            {value: 900, title: "15 minutes"},
            {value: 1800, title: "30 minutes"},
            {value: 3600, title: "1 hour"},
            {value: 5400, title: "1,5 hour"},
            {value: 10800, title: "3 hours"},
            {value: 18000, title: "5 hours"},
            {value: 28800, title: "8 hours"}
        ];

        main.heatingTimeout = main.heatingPeriodOptions[0];
        main.heatingDuration = main.heatingDurationOptions[0];
        function setConnected(connected) {
            $("#connect").prop("disabled", connected);
            $("#disconnect").prop("disabled", !connected);
        }

        main.connect = function () {
            loadInitData();
            var socket = new SockJS('/gs-guide-websocket');
            stompClient = Stomp.over(socket);
            stompClient.connect({}, function (frame) {
                setConnected(true);
                console.log('Connected: ' + frame);
                stompClient.subscribe('/topic/greetings', function (greeting) {
                    showGreeting(JSON.parse(greeting.body));
                });
            });
        }

        main.disconnect = function () {
            if (stompClient != null) {
                stompClient.disconnect();
            }
            setConnected(false);
            console.log("Disconnected");
            main.dataFromRaspberry = [];

        }

        function showGreeting(message) {
            $scope.$apply(function () {
                if(main.dataFromRaspberry.length>10) {
                    main.dataFromRaspberry.pop();
                }
                main.dataFromRaspberry.unshift(message);
            });
        }

        main.enableDisableHeating = function () {
            $scope.$apply(function () {

                $http({
                    url: "/change_heating",
                    method: "GET",
                    params: {
                        heating: $("#heating")[0].checked,
                        periodicalHeating: $("#periodicalHeating")[0].checked,
                        periodTimeout: main.heatingTimeout.value,
                        heatingDuration: main.heatingDuration.value
                    }
                }).then(function (response) {
                    main.heatingStatus = response.data;
                });

            });

        }

        main.next = function () {
                $http({
                    url: "/get_data",
                    method: "GET",
                    params: {
                        page: ++main.currentPage
                    }
                }).then(function (response) {
                    main.dataFromRaspberry = response.data;
                });
        }

        main.previous = function () {
            if(main.currentPage===0){
                return;
            }
            $http({
                url: "/get_data",
                method: "GET",
                params: {
                    page: --main.currentPage
                }
            }).then(function (response) {
                main.dataFromRaspberry = response.data;
            });
        }

        function loadInitData() {
            $http.get('./get_data').then(function (response) {
                main.dataFromRaspberry = response.data;
                main.currentPage=0;
            });
            $http.get('./get_heating_data').then(function (response) {
                main.heatingStatus = response.data;
                if (response.data.heatingStatus === true) {
                    $('#heating').bootstrapToggle('on');
                }
                if (response.data.enablePeriodicalHeating === true) {
                    $('#periodicalHeating').bootstrapToggle('on');
                }
                var found;
                main.heatingPeriodOptions.some(function (obj) {
                    if (obj.value === response.data.periodInSeconds) {
                        found = obj;
                        return true;
                    }
                });
                var found2;
                main.heatingDurationOptions.some(function (obj) {
                    if (obj.value === response.data.heatingDurationInSeconds) {
                        found2 = obj;
                        return true;
                    }
                });
                main.heatingDuration = found2;
                main.heatingTimeout = found;
                $(function () {
                    $('#heating').on('change', function (event) {
                        event.preventDefault(); // To prevent following the link (optional)
                        main.enableDisableHeating();
                    });
                    $('#periodicalHeating').on('change', function (event) {
                        event.preventDefault(); // To prevent following the link (optional)
                        main.enableDisableHeating();
                    });

                });
            });


        }


        main.connect();


    });