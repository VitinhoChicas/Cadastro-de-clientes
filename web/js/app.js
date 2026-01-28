
$(document).ready(function() {
    
    if ($.fn.DataTable) {
        $.extend(true, $.fn.dataTable.defaults, {
            "language": {
                "sEmptyTable": "Nenhum registro encontrado",
                "sInfo": "Mostrando de _START_ até _END_ de _TOTAL_ registros",
                "sInfoEmpty": "Mostrando 0 até 0 de 0 registros",
                "sInfoFiltered": "(Filtrados de _MAX_ registros)",
                "sInfoPostFix": "",
                "sInfoThousands": ".",
                "sLengthMenu": "_MENU_ resultados por página",
                "sLoadingRecords": "Carregando...",
                "sProcessing": "Processando...",
                "sZeroRecords": "Nenhum registro encontrado",
                "sSearch": "Pesquisar",
                "oPaginate": {
                    "sNext": "Próximo",
                    "sPrevious": "Anterior",
                    "sFirst": "Primeiro",
                    "sLast": "Último"
                },
                "oAria": {
                    "sSortAscending": ": Ordenar colunas de forma ascendente",
                    "sSortDescending": ": Ordenar colunas de forma descendente"
                }
            }
        });
    }

  
   

    // Confirmação de exclusão
    $('.btn-delete').on('click', function(e) {
        if (!confirm('Deseja realmente excluir este registro?')) {
            e.preventDefault();
        }
    });

    // Validação de formulário
    $('form').on('submit', function(e) {
        var nome = $('#nome').val();
        if (nome && nome.trim() === '') {
            alert('Por favor, preencha o nome do cliente.');
            e.preventDefault();
            return false;
        }
    });
});
