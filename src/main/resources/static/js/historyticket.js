var  myList=new Vue({
    el:"#mainFrame",
    data:{
        currentPage:1,
        totalPages:10,
        per:10,
        start:1,
        end:10,
        datas:[],
        loadTime:"",
        goodid:""
    },
    mounted:function () {
        this.showList()
    },
    methods:{
        showList:function(){
            $.ajax({
                type:"GET",
                url:"/ticket/history",
                data:{
                    currentPage:this.currentPage,
                    per:this.per,
                    loadTime:this.loadTime,
                    goodid:this.goodid
                },
                dataType:"json",
                success:function(data){
                    myList.datas=[];
                    myList.currentPage=data.currentPage,
                        myList.totalPages=data.totalPages;
                    myList.start=data.start;
                    myList.end=data.end;
                    var list=data.datas;

                    for (var i=0;i<list.length;i++){
                        myList.datas.push(list[i]);
                    }
                }
            })
        },
        del:function(id){
            if (confirm("您确定要删除吗？（会将存储信息一同删除）")) {
                /*根据id删除*/
                $.ajax({
                    type: "POST",
                    url: "/ticket/" + id,
                    success: function (data) {
                        if (data == "true") {
                            alert("删除成功");
                            myList.showList();

                        }else{
                            alert("删除失败");
                        }
                    }
                });
            }
        },
        setQuery:function(){
            this.loadTime=$("#Calendar").val();
            this.goodid=$("#goodid").val();

            this.showList();
        },
        toPage:function(page){
            this.currentPage=page;
            this.showList();
        }
    }
});