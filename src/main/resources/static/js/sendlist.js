var  myVue = new Vue({
    el:"#line",
    data:{
      fromLine:[],
      toLine:[]
    },
    mounted:function(){
        this.showList()
    },
    methods: {
        showList: function () {
            $.ajax({
                type: "GET",
                url: "/personal/loadplace",
                success: function (data) {
                    myVue.fromLine = [];
                    for (var i = 0; i < data.length; i++) {
                        myVue.fromLine.push(data[i]);
                        myVue.toLine.push(data[i]);
                    }
                }
            });
        },
        changeFromLine: function (temp) {
            $("#from").html(temp);
            $("input[name='fromLine']").val(temp);
        },
        changeToLine: function (temp) {
            $("#to").html(temp);
            $("input[name='toLine']").val(temp);
        }
    }
});
function download(id){
    var a = document.createElement('a');
    var url = "/download?id="+id+"&type="+2;
    a.href=url;
    a.click()
}
function checkMsg() {
    var carid=$("#carid").val();

    var place=$("#to").html();

    var calendar=$("#Calendar").val();

    if (testNull(carid)){
        alert("承运车号不能为空");
        return;
    }
    if (testNull(place)){
        alert("标题目的地不能为空");
        return ;
    }
    if (testNull(calendar)){
        alert("装车日期不能为空");
        return ;
    }

    $("#myForm").submit();
}
function testNull(a){
    if (a == "" || a == null || a == undefined){
        return true;
    }
    return false;
}