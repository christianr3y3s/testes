angular.module("getbooks.services", ["ngResource"]).
    factory('Book', function ($resource) {
        var Book = $resource('/api/v1/books/:bookId', {bookId: '@id'});
        Book.prototype.isNew = function(){
            return (typeof(this.id) === 'undefined');
        }
        return Book;
    });

angular.module("getbooks", ["getbooks.services"]).
    config(function ($routeProvider) {
        $routeProvider
            .when('/api/v1/books', {templateUrl: '/assets/views/books/list.html', controller: BookListController})
            .when('/api/v1/book', {templateUrl: '/assets/views/books/create.html', controller: BookCreateController})
            .when('/api/v1/books/:bookId', {templateUrl: '/assets/views/books/detail.html', controller: BookDetailController});
    });

function BookListController($scope, Book) {
    $scope.books = Book.query();
    
}

function BookCreateController($scope, $routeParams, $location, Book) {

    $scope.book = new Book();

    $scope.save = function () {
    	$scope.book.$save(function (book, headers) {
    		toastr.success("Submitted New Book");
            $location.path('/');
        });
    };
}


function BookDetailController($scope, $routeParams, $location, Book) {
    var bookId = $routeParams.bookId;
    
    $scope.bookId = Book.get({bookId: bookId});

}
