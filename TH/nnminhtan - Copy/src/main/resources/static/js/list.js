// $(document).ready(function () {
//     $.ajax({
//         url: 'http://localhost:8080/api/v1/books',
//         type: 'GET',
//         dataType: 'json',
//         success: function (data) {
//             let trHTML = '';
//             $.each(data, function (i, item) {
//                 trHTML = trHTML + '<tr id="book-' + item.id + '">' +
//                     '<td>' + item.id + '</td>' +
//                     '<td>' + item.title + '</td>' +
//                     '<td>' + item.author + '</td>' +
//                     '<td>' + item.price + '</td>' +
//                     '<td>' + item.category + '</td>' +
//                     '<td>';
//
//                 trHTML += '<div>' + // assuming sec:authorize works server-side and generates correct HTML
//                     '<a href="/books/edit/' + item.id + '" class="btn btn-success btn-sm">Edit</a>' +
//                     '<a href="/books" class="btn btn-danger btn-sm" onClick="return apiDeleteBook(' + item.id + ');">Delete</a>' +
//                     '</div>';
//
//                 // if (userRole === 'ADMIN') {
//                 //     trHTML += '<a href="/books/edit/' + item.id + '" class="btn btn-success btn-sm">Edit</a>' +
//                 //         '<a href="#" class="btn btn-danger btn-sm" onClick="return confirmDelete(' + item.id + ');">Delete</a>';
//                 // }
//                 trHTML += '</td></tr>';
//                 //
//                 // '<a href="#' + item.id + '" class="text-primary">Edit</a> | ' +
//                 // '<a href="/books' + item.id + 'class="text-danger" onclick="apiDeleteBook(' + item.id + ', this); return false;" sec:authorize="hasAuthority(\'ADMIN\')">Delete</a>' +
//                 // '</td>' +
//                 // '</tr>';
//             });
//             $('#book-table-body').append(trHTML);
//         }
//     });
// });
//
// function apiDeleteBook(id) {
//     if(confirm('Are you sure you want to delete?')) {
//         $.ajax({
//             url: 'http://localhost:8080/api/v1/books/' + id,
//             type: 'DELETE',
//             success: function () {
//                 alert('Book deleted successfully!');
//                 $('#book-' + id).remove();
//             }
//         });
//     }
// }
// function editBook(id) {
//     $.ajax({
//         url: `/api/v1/books/${id}`,
//         type: 'GET',
//         success: function(book) {
//             $('#Id').val(book.id);
//             $('#title').val(book.title);
//             $('#author').val(book.author);
//             $('#price').val(book.price);
//             $('#category').val(product.category);
//         }
//     });
// }