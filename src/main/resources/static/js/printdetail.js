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
        download:function(id){
            var a = document.createElement('a');
            var url = "/download?id="+id+"&type="+2;
            a.href=url;
            a.click()
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