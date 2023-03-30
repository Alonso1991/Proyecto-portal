JavaScript
$(document).ready(function(){
    $('#table').Tabledit({
        deleteButton: false,
        editButton: true,
        columns: {
          identifier: [0, 'id'],
          editable: [[1, 'Rol'], [2, 'Status']]
        },
        hideIdentifier: true,
        url: 'editarCelda.php'
    });
});