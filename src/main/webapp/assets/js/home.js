$(function () {
    getSpendSituation();
    getSpendTrend();
    getUserInfo()
    getCategory()
});
var urlHead = "/user";
var deleteId = 0;

function getUserInfo() {
    var type = "getUserInfo";
    var urlPath = urlHead + "?method=" + type;
    $.ajax({
        url: urlPath,
        data: {},
        type: 'post',
        cache: false,
        dataType: 'json',
        success: function (data) {
            $('#username').html("你好，" + data.name + " 😄");

        },
        error: function () {
            alert("异常！");
        }
    });

}

function getCategory() {
    var type = "getCategory";
    var urlPath = urlHead + "?method=" + type;
    var content = "";
    var list = [];

    //设置需要显示的列
    var columns = [{
        checkbox: true
    }, {
        field: 'name',
        title: '类别名称'
    }];

    $('#showcategory').bootstrapTable({
        url: urlPath,
        method: 'POST',
        singleSelect: false,
        clickToSelect: true,
        pagination: true,//是否分页
        striped: true,//隔行变色
        pageSize: 5,//每页行数
        columns: columns,
        onLoadSuccess: function (data) {
            for (var i = 0; i < data.length ; i++) {
                content += " <option value='Value'>"+ data[i].name + "</option> ";
            }

            $('#selectCategory').html(content);
        },
        onLoadError: function () {
            showTips("数据加载失败！");
        },
        onCheck: function (row, $element) {
            // alert(JSON.stringify(row));
            deleteId = row.id;
            console.log(row.name);
            $('#deleteCategory').attr("placeholder", row.name);
            $('#originCategory').attr("placeholder", row.name);
        }
    });

}

function updateCategory() {

    var type = "updateCategory";
    var urlPath = urlHead + "?method=" + type;
    var updateName = $('#updateCategory').val();
    $.ajax({
        url: urlPath,
        data:  {"id": deleteId,"name":updateName},
        type: 'post',
        cache: false,
        dataType: 'json',
        success: function (data) {
            if (data.msg == "success") {

                $('#showcategory').bootstrapTable('refresh', {pageSize: 5});
                alert(" 更新成功 👍");

            } else {
                alert(" 更新失败 😂");
            }


        },
        error: function () {
            alert("异常！");
        }
    });
}

function insertCategory() {
    var type = "insertCategory";
    var urlPath = urlHead + "?method=" + type;
    var addName = $('#insertCategory').val();
    $.ajax({
        url: urlPath,
        data: {"name": addName},
        type: 'post',
        cache: false,
        dataType: 'json',
        success: function (data) {
            if (data.msg == "success") {

                $('#showcategory').bootstrapTable('refresh', {pageSize: 5});
                alert(" 添加成功 👍");

            } else {
                alert(" 添加失败 😂");
            }


        },
        error: function () {
            alert("异常！");
        }
    });


}

function deleteCategory() {
    var type = "deleteCategory";
    var urlPath = urlHead + "?method=" + type;
    $.ajax({
        url: urlPath,
        data: {"id": deleteId},
        type: 'post',
        cache: false,
        dataType: 'json',
        success: function (data) {
            if (data.msg == "success") {

                $('#showcategory').bootstrapTable('refresh', {pageSize: 5});
                alert(" 删除成功 👍");

            } else {
                alert(" 删除失败 😂");
            }


        },
        error: function () {
            alert("异常！");
        }
    });


}


function showNewRecord() {
    var selectCategory = $('#selectCategory option:selected').text();
    var moneySpend = $('#moneySpend').val();
    var recordDate = $('#recordDate').val();
    var recordComment = $('#recordComment').val();
    $('#newCategory').attr("placeholder",selectCategory);
    $('#newMoney').attr("placeholder",moneySpend);
    $('#newDate').attr("placeholder",recordDate);
    $('#newComment').attr("placeholder",recordComment);


}

function recordBill() {
    var type = "recordBill";
    var urlPath = urlHead + "?method=" + type;
    var selectCategory = $('#selectCategory option:selected').text();
    var moneySpend = $('#moneySpend').val();
    var recordDate = $('#recordDate').val();
    var recordComment = $('#recordComment').val();


    var n = /^([-+]?\d{0,8})(\.\d{2})?$/;
    var re = new RegExp(n);
    if (!re.test(moneySpend)){
        alert("输入整数位数应小于等于8位，小数位数小于等于2位");
    }
    else {
        $.ajax({
            url: urlPath,
            data: {"spend": moneySpend,"date":recordDate,"category":selectCategory,"comment":recordComment},
            type: 'post',
            cache: false,
            dataType: 'json',
            success: function (data) {
                if (data.msg == "success") {

                    toastr.success('记录成功 😄');

                } else {
                    toastr.error('记录失败 😂');
                }


            },
            error: function () {
                alert("异常！");
            }
        });

    }


}

function ClearRecordBill() {
    $('#moneySpend').val("");
    $('#recordDate').val("");
    $('#recordComment').val("");

}

function getSpendSituation() {


    var type = "getSpendSituation";
    var urlPath = urlHead + "?method=" + type;
    var list = [['product', 'nums']];
    $.ajax({
        url: urlPath,
        data: {},
        type: 'post',
        cache: false,
        dataType: 'json',
        success: function (data) {
            for (var i = 0; i < data.length ; i++) {
                list.push([data[i].name,data[i].spend]);
            }

            option.dataset.source = list;
            myChart.setOption(option);
        },
        error: function () {
            alert("异常！");
        }
    });



    var myChart = echarts.init(document.getElementById('billpiechart'));
    var option = {
        dataset: {
            source: [['product', 'nums'],['测试',2]]
        },
        color: ['#d74e67', '#0092ff', '#eba954', '#21b6b9', '#60a900', '#01949b', '#f17677'],
        grid: {
            left: '50%',
            top: 'center',
            right: '1%',
            containLabel: true
        },
        xAxis: [
            {
                show: false
            }, {
                show: false
            }
        ],
        yAxis: {
            type: 'category',
            inverse: true,
            show: false
        },

        series: [{
            tooltip: {
                trigger: 'item',
                formatter: "{b} : {c} ({d}%)"
            },
            type: 'pie',
            center: ['22%', '50%'],
            radius: ['20%', '30%'],
            avoidLabelOverlap: false,

            label: {
                normal: {
                    formatter: '{b}\n{d}%'
                },

            },
            labelLine: {
                normal: {
                    show: true
                }
            },
            encode: {
                itemName: 'product',
                value: 'nums'
            }
        },
            //亮色条 百分比
            {
                show: true,
                type: 'bar',
                barGap: '-100%',
                barWidth: '20%',
                z: 2,
                color: function (params) {
                    // build a color map as your need.
                    var colorList = [
                        '#d74e67', '#0092ff', '#eba954', '#21b6b9', '#60a900', '#01949b', '#f17677'
                    ];
                    return colorList[params.dataIndex]
                },
                label: {
                    normal: {
                        show: true,
                        textStyle: {
                            color: '#000',
                            fontSize: 12,
                            fontWeight: 'bold'
                        },
                        position: 'left'
                    }
                },
                encode: {
                    x: 'nums'
                }
            },
            //年份
            {
                show: true,
                type: 'bar',
                xAxisIndex: 1, //代表使用第二个X轴刻度
                barGap: '-100%',
                barWidth: '10%',
                itemStyle: {
                    normal: {
                        barBorderRadius: 200,
                        color: 'transparent'
                    }
                },
                label: {
                    normal: {
                        show: true,
                        position: [0, '-20'],
                        formatter: '{b}',
                        textStyle: {
                            fontSize: 14,
                            color: '#333',
                        }
                    }
                },
                encode: {
                    y: 'product'
                }
            }
        ]
    };

    myChart.setOption(option);
}


function getSpendTrend() {

    var type = "getSpendTrend";
    var urlPath = urlHead + "?method=" + type;
    var days = [],spend = [];
    $.ajax({
        url: urlPath,
        data: {"month":0},
        type: 'post',
        cache: false,
        dataType: 'json',
        success: function (data) {
            for (var i = 0; i < data.length ; i++) {
              console.log(data[i]);
              days.push(data[i].days + '日');
              spend.push(data[i].spend);
                option.xAxis.data = days;
                option.series[0].data = spend;
                myChart.setOption(option);

            }

            // option.dataset.source = list;
            // myChart.setOption(option);
            // console.log(list);
        },
        error: function () {
            alert("异常！");
        }
    });





    var myChart = echarts.init(document.getElementById('billlinechart'));

    var option = {
        xAxis: {
            type: 'category',
            data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
        },
        yAxis: {
            type: 'value'
        },
        series: [{
            data: [120, 200, 150, 80, 70, 110, 130],
            type: 'bar'
        }]
    };
    myChart.setOption(option);

}
