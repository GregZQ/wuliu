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
        carid:""
    },
    mounted:function () {
        this.showList()
    },
    methods:{
        showList:function(){
            $.ajax({
                type:"GET",
                url:"/senddetails/query",
                data:{
                    currentPage:this.currentPage,
                    per:this.per,
                    loadTime:this.loadTime,
                    carid:this.carid
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
            if (confirm("您确定要删除吗？（会将运货信息一同删除）")) {
                /*根据id删除*/
                $.ajax({
                    type: "POST",
                    url: "/senddetails/" + id,
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
            this.carid=$("#carid").val();

            this.showList();
        },
        toPage:function(page){
            this.currentPage=page;
            this.showList();
        }
    }
});