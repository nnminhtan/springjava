$(document).ready(function () {
    $.ajax({
        url: '/api/v1/books',
        type: 'GET',
        dataType: 'json',
        success: function (response) {
            let trHTML = '';
            $.each(response, function (i, item) {
                trHTML += '<tr id="book-' + item.id + '">' +
                    '<td>' + item.id + '</td>' +
                    '<td>' + item.title + '</td>' +
                    '<td>' + item.author + '</td>' +
                    '<td>' + item.price + '</td>' +
                    '<td>' + item.category + '</td>';
                let isAdmin = $('th:contains("Action")').length > 0; // Kiểm tra xem cột Action có tồn tại không
                if (isAdmin) {
                    trHTML += '<td>' +
                        '<a href="#" class="text-primary" onclick="apiEditBook(' + item.id + '); return false;">Edit</a> | ' +                        // '<a href="#" class="text-primary" onclick="apiEditBook(' + item.id + '); return false;">Edit</a> | ' +
                        '<a href="#" class="text-danger" onclick="apiDeleteBook(' + item.id + '); return false;">Delete</a>' +
                        '</td>';
                }
                // let isUser = $('th:contains("Add to Cart")').length > 0;
                // if (isUser) {
                //     trHTML += '<td>' +
                //         '<form th:action="@{/add-to-cart}" method="post" class="d-inline">' +
                //         '<input type="hidden" name="id" value="' + item.id + '">' +
                //         '<input type="hidden" name="name" value="' + item.title + '">' +
                //         '<input type="hidden" name="price" value="' + item.price + '">' +
                //
                //         '<button type="submit" class="btn btn-success" onclick="return confirm(\'Are you sure you want to add this book to cart?\')">Add to cart</button>' +
                //         '</form>' +
                //         '</td>';
                // }

                trHTML += '</tr>';
            });
            $('#book-table-body').append(trHTML);
        }
    });
});

function apiDeleteBook(id) {
    if (confirm('Are you sure you want to delete?')) {
        $.ajax({
            url: '/api/v1/books/' + id,
            type: 'DELETE',
            success: function () {
                alert('Book deleted successfully!');
                $('#book-' + id).remove();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert('Failed to delete book: ' + textStatus);
                console.error('Error:', errorThrown);
            }
        });
    }
}

// function apiUpdateBook(id, updatedBook) {
//     $.ajax({
//         url: '/api/v1/books/edit/' + id,
//         type: 'PUT',
//         contentType: 'application/json',
//         data: JSON.stringify(updatedBook),
//         success: function (response) {
//             alert('Book updated successfully!');
//             // Optionally, update the table row with the new data
//             $('#book-' + id + ' td:nth-child(2)').text(response.title);
//             $('#book-' + id + ' td:nth-child(3)').text(response.author);
//             $('#book-' + id + ' td:nth-child(4)').text(response.price);
//             $('#book-' + id + ' td:nth-child(5)').text(response.category);
//         },
//         error: function (jqXHR, textStatus, errorThrown) {
//             alert('Failed to update book: ' + textStatus);
//             console.error('Error:', errorThrown);
//         }
//     });
// }
function apiEditBook(id) {
    window.location.href = "/books/edit/" + id;
}