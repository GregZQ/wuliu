var myVue = new Vue({
	el:"#modalForm",
	data:{
		fromList:[],
		toList:[],
		fromPlace:"",
		toPlace:""
	},
	mounted:function () {
		this.showList()
    },
	methods:{
		showList:function(){
			$.ajax({
				type:"GET",
				url:"/personal/place",
				success:function(data){
					myVue.fromList=[];
					for (var i=0;i<data.length;i++){
						myVue.fromList.push(data[i]);
                        myVue.toList.push(data[i]);
					}
				}
			});
		},
        addFromPlace:function(val){
			alert(val);
    		fromPlace=val
		},
        sureFromSave:function(){
			if (!(fromPlace=="")){
				$("#from_val").val(fromPlace);
			}
		},
		addToPlace:function (val) {
			toPlace=val;
        },
		sureToSave:function () {
            if (!(toPlace=="")){
                $("#to_val").val(toPlace);
            }
        }
	}

});

var datas=["零","壹","贰","叁","肆","伍","陆","柒","捌","玖"];
var dw=["","拾","佰","仟","万"];
$('#money_input').bind('input propertychange',function(){
		var val=this.value;
		val=val.trim();//获取输入的价格
		var tag=0;
		var jw=0;//获取单位
		var result;//获取每位的值
		while(1){
			result=val%10;//获得余数
			if (jw<=3&&(tag==1||result>0)){
				$("#m"+jw).html(datas[result]);
                tag=1;
			}
			else if(jw<=3){
				$("#m"+jw).html("");
			}
			else{
				//最后一位
                jw=0;
                tag=0;
                var head="";
				while(val>0){
					result=val%10;//从后向前获取值
					if (result>0) {
                        head = (datas[result] + dw[jw] + head);
                    	tag=1;
					}
					else if (tag==1){
						head=datas[result]+head;
						tag=0;
					}
					jw++;
					val=parseInt(val/10);
				}
				$("#m4").html(head);
				break;
			}
			jw++;
			val=parseInt(val/10);
		}
	
});


var from_places=["聊城","东昌府","临清","高唐","冠","阳谷","荏平","莘","东阿"];

var fromPlace;
function addFromPlace(val){
	fromPlace=val
}
function sureFromSave(){
	if (!(fromPlace=="")){
		$("#from_val").val(fromPlace);
	}
}

var toPlace1=["黑龙江","吉林","辽宁"];

var toPlace2=[["哈尔滨","齐齐哈尔","鸡西","鹤岗","双鸭山","大庆","伊春","佳木斯","七台河","牡丹江","黑河","绥化","大兴安岭"],
  			  ["长春","吉林","四平", "辽源", "通化","白山","松原", "白城","延边州"],
  			  ["沈阳","大连","鞍山","抚顺","本溪","丹东","锦州", "营口", "阜新","辽阳","盘锦","铁岭", "朝阳", "葫芦岛"]
  			 ];

var toPlace3=[  [   
                    ["道里", "南岗", "道外", "平房", "松北", "香坊", "呼兰", "阿城", "双城", "依兰", "方正", "宾县", "巴彦", "木兰", "通河", "延寿", "尚志", "五常"],
                    ["龙沙", "建华", "铁锋", "昂昂溪", "富拉尔基", "碾子山", "梅里斯", "龙江", "依安", "泰来", "甘南", "富裕", "克山", "克东", "拜泉", "讷河"],
                    ["鸡冠", "恒山", "滴道", "梨树", "城子河", "麻山", "鸡东", "虎林", "密山"],
                    ["向阳", "工农", "南山", "兴安", "东山", "兴山", "萝北", "绥滨"],
		            ["尖山", "岭东", "四方台", "宝山", "集贤", "友谊", "宝清", "饶河"],
					["萨尔图", "龙凤", "让胡路", "红岗", "大同", "肇州", "肇源", "林甸", "杜尔伯特"],
		            ["伊春", "南岔", "友好", "西林", "翠峦", "新青", "美溪", "金山屯", "五营", "乌马河", "汤旺河", "带岭", "乌伊岭", "红星", "上甘岭", "嘉荫", "铁力"],
					["向阳", "前进", "东风", "郊区", "桦南", "桦川", "汤原", "同江", "富锦", "抚远"],
					["新兴", "桃山", "茄子河", "勃利"],
					["东安", "阳明", "爱民", "西安", "林口", "绥芬河", "海林", "宁安", "穆棱", "东宁"],
					["爱辉", "嫩江", "逊克", "孙吴", "北安", "五大连池"],
					["北林", "望奎", "兰西", "青冈", "庆安", "明水", "绥棱", "安达", "肇东", "海伦"],
					["呼玛", "塔河", "漠河"]
                 ],
                 [
                 	["南关", "宽城", "朝阳", "二道", "绿园", "双阳", "九台", "农安", "榆树", "德惠"],
					["昌邑", "龙潭", "船营", "丰满", "永吉", "蛟河", "桦甸", "舒兰", "磐石"],
					["铁西", "铁东", "梨树", "伊通", "公主岭", "双辽"],
					["龙山", "西安", "东丰", "东辽"],
					["东昌", "二道江", "通化", "辉南", "柳河", "梅河口", "集安"],
					["浑江", "江源", "抚松", "靖宇", "长白", "临江"],
					["宁江", "前郭尔罗斯", "长岭", "乾安", "扶余"],
					["洮北", "镇赉", "通榆", "洮南", "大安"],
					["延吉", "图们", "敦化", "珲春", "龙井", "和龙", "汪清", "安图"]
                 ],
                 [
                 	["和平", "沈河", "大东", "皇姑", "铁西", "苏家屯", "浑南", "沈北新", "于洪", "辽中", "康平", "法库", "新民"],
					["中山", "西岗", "沙河口", "甘井子", "旅顺口", "金州", "普兰店", "长海", "瓦房店", "庄河"],
					["铁东", "铁西", "立山", "千山", "台安", "岫岩", "海城"],
					["新抚", "东洲", "望花", "顺城", "抚顺", "新宾", "清原"],
					["平山", "溪湖", "明山", "南芬", "本溪", "桓仁"],
					["元宝", "振兴", "振安", "宽甸", "东港", "凤城"],
					["古塔", "凌河", "太和", "黑山", "义县", "凌海", "北镇"],
					["站前", "西", "鲅鱼圈", "老边", "盖州", "大石桥"],
					["海州", "新邱", "太平", "清河门", "细河", "阜新", "彰武"],
					["白塔", "文圣", "宏伟", "弓长岭", "太子河", "辽阳", "灯塔"],
					["双台子", "兴隆台", "大洼", "盘山"],
					["银州", "清河", "铁岭", "西丰", "昌图", "调兵山", "开原"],
					["双塔", "龙城", "朝阳", "建平", "喀喇沁左翼", "北票", "凌源"],
					["连山", "龙港", "南票", "绥中", "建昌", "兴城"]
                 ]
             ];


/**
 * 显示,
 */
function showToPlace2(id){
	var length=toPlace2[id].length;
	$("#to2").empty();
	$("#to3").empty();
	$("#to2").css("display","block");
	for (i=0;i<length;i++){
		$("#to2").append(
			'<label><input type="radio" name="to2" onclick="showToPlace3('+id+','+i+')" onchange="addToPlace('+id+','+i+','+null+','+'2)">'+toPlace2[id][i]+ '</label>',
		);
	}
}
/**
 * 显示级别
 */
function showToPlace3(pro,city){
		
		var length=toPlace3[pro][city].length;
		$("#to3").empty();
		$("#to3").css("display","block");		
		for (i=0;i<length;i++){
			$("#to3").append(
				'<label><input type="radio" name="to3" onchange="addToPlace('+pro+','+city+','+i+','+'3)">'+toPlace3[pro][city][i]+ '</label>',
			);
		}	
}
var toPlace;
function addToPlace(num1,num2,num3,tag){
	switch (tag){
	  case 1:
          toPlace=toPlace1[num1];
	    break;
	  case 2:
          toPlace=toPlace2[num1][num2];
	    break;
	   case 3:
            toPlace=toPlace3[num1][num2][num3];
		break;
    }
}

function sureToSave(){
	if (!(fromPlace=="")){
		$("#to_val").val(toPlace);
	}
}

/*
 * 数字这一个地方没搞完
 * */



/**
 * 数据的比较
 * @param {Object} num1
 * @param {Object} num2
 */
function getResult(num1,num2){

    var float1=parseFloat(num1);
    var float2=parseFloat(num2);
    var  result;
    if (!(typeof num1  == "undefined") && !( num1 == null) && !(num1.trim() == "")
        &&!(typeof num2  == "undefined") && !( num2 == null) && !(num2.trim() == "")){

        result=float1*float2;
        result=result.toFixed(2);
        $("#moneyResult").val(result);
    }

}
/*监听input的变化*/
$('#count').bind("input propertychange",function(){
    var count=$("#count").val();
    var per=$("#per").val();
    if (!(typeof count  == "undefined") && !( count == null) && !(count.trim() == "")){
        $("#cube").attr("readonly","readonly");
    }else{
        $("#moneyResult").val("");
        $("#cube").removeAttr("readonly");
    }
    getResult(count,per);
});

$('#cube').bind("input propertychange",function(){
    var per=$("#per").val();
    var cube=$("#cube").val();
    if (!(typeof cube  == "undefined") && !( cube == null) && !(cube.trim() == "")){
        $("#count").attr("readonly","readonly");
    }else{
        $("#moneyResult").val("");
        $("#count").removeAttr("readonly");
    }

    getResult(cube,per);
});

$("#per").bind("input propertychange",function(){

    var count=$("#count").val();
    var cube=$("#cube").val();
    var per=$("#per").val();
    if ((typeof per  == "undefined") || ( per == null) || (per.trim() == "")){
        $("#moneyResult").val("");
        return ;
    }
    getResult(count,per);

    getResult(cube,per);

});
