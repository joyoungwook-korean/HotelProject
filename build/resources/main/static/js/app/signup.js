var main = {

    init: function () {
        var _this = this;

        $('#userid').on('input',function () {
            _this.check_id();
        });



        $('#btn-update').on('click', function () {
            _this.update();
        });

        $('#btn-delete').on('click', function () {
            _this.delete();
        });
    },
    check_id: function () {
        var userid =  $('#userid').val();


        $.ajax({
            url: '/signup/signupForm/check',
            type: 'post',
            data: userid,
            contentType: 'application/json; charset=utf-8'
        }).done(function (cnt) {
            if(cnt==1){
                $('#check_id_ok').hide();
                $('#check_id_no').show();
                $('#btn-save').attr('disabled',true);
            }else{
                $('#check_id_ok').show();
                $('#check_id_no').hide();
                $('#btn-save').attr('disabled',false);
            }
        }).fail(function (error) {
            $('#check_id_no').hide();
            $('#check_id_ok').hide();
        });
    },



    update: function () {
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('글이 수정 되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });

    },

    delete: function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function () {
            alert("글이 삭제 되었습니다");
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};

main.init();