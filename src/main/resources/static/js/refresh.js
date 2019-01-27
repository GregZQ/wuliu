
var rightCon=new Vue({
    el:"#mainFrame",
    data:{
        currentPage:1,
        totalPages:10,
        per:10,
        start:1,
        end:10,
        datas:[],
        selects:[]
    },
    mounted:function(){
            this.showDetails();
    },
    updated:function(){
        for (i=0;i<rightCon.datas.length;i++){//所用的数据
            for (j=0;j<rightCon.selects.length;j++){//被选中的id的值
                id=rightCon.datas[i].id;
                if (id==rightCon.selects[j]){

                   document.getElementById(id).checked=true; //这样才会起作用
                    break;
                }
            }
        }
    },
    methods:{
        showDetails:function(){
            $.ajax({
                type:"GET",
                url:"/detail/list",
                data:{
                    currentPage:this.currentPage,
                    per:this.per
                },
                dataType: "json",
                success:function(data){
                    rightCon.datas=[];
                    rightCon.currentPage=data.currentPage;
                    rightCon.totalPages=data.totalPages;
                    rightCon.start=data.start;
                    rightCon.end=data.end;
                    var list=data.datas;
                    for (var i=0;i<list.length;i++){

                        rightCon.datas.push(list[i]);

                    }
                    $("[name='checkbox']").attr("checked",false);
                }
            });
        },
        toPage:function(currentPage){
            this.currentPage=currentPage;
            //先进行页面checkbox清空
            this.showDetails();

        },
        showSelected:function(){

        },
        download:function(id){
            var a = document.createElement('a');
            var url = "/download?id="+id+"&type="+1;
            a.href=url;
            a.click()
        },
        del:function (id) {
            if (confirm("您确认删除吗?")) {
                $.ajax({
                    type: "POST",
                    url: "/detail/remove/" + id,
                    success: function (data) {
                        if (data == "true") {
                            rightCon.showDetails();
                        }
                    }
                });
            }
        },
        addSelects:function(e){
            if ($("#"+e).is(':checked')==true){
                this.selects.push(e);
            }
            else{
                for (var i=0;i<this.selects.length;i++){
                    if (this.selects[i]==e){
                        this.selects.splice(i,1);
                    }
                }
            }
        },
        /*这个对应于发货细节*/
        senddetils:function(){

            if (this.selects==null||this.selects.length==0){
                alert("请至少选择一项内容");
                return ;
            }
            var body = $(document.body);
            var form=$("<form method='post'></form>");
            form.attr({"action":"/adddetail"});
            var input=$("<input type='hidden'>");

            input.attr({"name":"selects"});
            input.val(this.selects);
            form.appendTo(document.body);
            form.append(input);
            form.submit();
        }
    }
});