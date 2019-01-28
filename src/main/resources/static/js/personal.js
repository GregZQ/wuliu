
var mainFram = new Vue({
	el:"#mainFrame",
	data:{
		company:"",
		placeList:[],
		loadPlaceList:[],
	},
	mounted:function(){
		this.showList();
	},
	methods:{
		showList:function(){
			$.ajax({
				type:"GET",
				url:"/personal/company",
				success:function(data){
					mainFram.company = data;
				}
			});
			$.ajax({
				type:"GET",
				url:"/personal/place",
				success:function(data){
					mainFram.placeList=[];
					for (var i =0; i<data.length;i++){
						mainFram.placeList.push(data[i]);
					}
				}
			});
			$.ajax({
				type:"GET",
				url:"/personal/loadplace",
				success:function(data){
					mainFram.loadPlaceList=[];
					for (var i =0 ;i < data.length;i++){
						mainFram.loadPlaceList.push(data[i]);
					}
				}
			});
		},
        deletePlace:function(id) {
            if (confirm("您确定要删除吗？")) {
				/*根据id删除*/
                $.ajax({
                    type: "GET",
                    url: "/personal/place/" + id,
                    success: function (data) {
                        if (data == "true") {
                            alert("删除成功");
                            mainFram.showList();

                        } else {
                            alert("删除失败");
                        }
                    }
                });
            }
        },
        deleteLoadPlace:function(id) {
            if (confirm("您确定要删除吗？")) {
				/*根据id删除*/
                $.ajax({
                    type: "GET",
                    url: "/personal/loadplace/" + id,
                    success: function (data) {
                        if (data == "true") {
                            alert("删除成功");
                            mainFram.showList();

                        } else {
                            alert("删除失败");
                        }
                    }
                });
            }
        }
	}
});

function submitCompany(){
	var val = $("#company1").val();
	
	if (!checkVal(val)){
		return ;
	}
	$("#company").attr("value",val);
	$("#companyForm").submit();
	
}

function submitPlace(){
	var val = $("#place2").val();
	if (!checkVal(val)){
		return;
	}
	$("#place").attr("value",val);
	$("#placeForm").submit();
}
function submitLoadPlace(){
	var val = $("#loadPlace2").val();
	if (!checkVal(val)){
		return;
	}
	$("#loadPlace").attr("value",val);
	$("#loadPlaceForm").submit();
}

function checkVal(value){
	if (value == null || value.trim() == ""){
		return false;
	}
	return true;
}
