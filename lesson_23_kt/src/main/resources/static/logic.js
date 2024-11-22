var app = angular.module('app', ['ngRoute']);
var contextPath = 'http://localhost:8080/app'

app.config(function ($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'about.html',
        })
        .when('/books', {
            templateUrl: 'books.html',
            controller: 'booksController'
        })
        .when('/books/add', {
            templateUrl: 'books-add.html',
            controller: 'booksAddController'
        })
        .when('/books/edit', {
            templateUrl: 'books-edit.html',
            controller: 'booksEditController'
        })
});

app.factory("myFactory", function() {
    var savedData = {}
    function set(data) {
        savedData = data
    }
    function get() {
        return savedData
    }

    return {
        set: set,
        get: get
    }
})


app.controller('booksController', function ($scope, $http, $location, myFactory) {
    fillTable = function () {
        $http.post(contextPath + '/api/v1/books/withFilter',$scope.filterAndPagination)
            .then(function (response) {
                $scope.BooksList = response.data;
            });
    };

    $scope.submitDeleteBook = function (deleteBookId) {
        $http.delete(contextPath + '/api/v1/books/' + deleteBookId)
            .then(function (response) {
                fillTable();
            });
    };

    $scope.changePage = function (pageNumber) {
                $scope.filterAndPagination.pagination = {}
                $scope.filterAndPagination.pagination.pageNumber = pageNumber;
                console.table($scope.filterAndPagination);
                fillTable();
            };

    $scope.submitFind = function () {
            console.table($scope.filterAndPagination);
            fillTable();
        };

    $scope.startEditBook = function (editBookId) {
        $http.get(contextPath + '/api/v1/books/' + editBookId)
            .then(function (response) {
                myFactory.set(response.data)
                console.table($scope.newBook);
                $location.path('books/edit');
            });
    };

    $scope.filterAndPagination = {};
    $scope._ = {};
    fillTable();

    $http.get(contextPath + '/api/v1/books/genres')
        .then(function (response) {
            $scope.GenresList = response.data;
        });
});

app.controller('booksAddController', function ($scope, $http, $location) {
    $scope.submitCreateNewBook = function () {
        $http.post(contextPath + '/api/v1/books', $scope.newBook)
            .then(function (response) {
                console.table(response.data);
                $location.path('books');
            });
    };
});

app.controller('booksEditController', function ($scope, $http, $location, myFactory) {
    $scope.submitEditBook = function () {
        $http.put(contextPath + '/api/v1/books', $scope.newBook)
            .then(function (response) {
                $location.path('books');
            });
    };

    $scope.newBook = myFactory.get();
});