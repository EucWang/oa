//时间控件的设置
$(".form_datetime1").datetimepicker({
        language: 'zh-CN',
        minView: 'month',
        autoclose: true,
        todayHighlight: true,
        format: 'yyyy-mm-dd',
        forceParse: true,
        todayBtn: true,
        initialDate: new Date()

    }
);
//表单校验的抽取, 表单的class属性中增加 form-validate
$(".form-validate").bootstrapValidator(
    {
        message: '这个值没有被验证',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            name: {
                validators: {
                    notEmpty: {
                        message: '用户名不能为空'
                    },
                    stringLength: {
                        min: 6,
                        max: 64,
                        message: '用户名长度在6到64之间'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_]+$/,
                        message: '用户名必须由字母数组和下划线组成'
                    }
                }
            },
            username: {
                validators: {
                    notEmpty: {
                        message: '用户名不能为空'
                    },
                    stringLength: {
                        min: 6,
                        max: 64,
                        message: '用户名长度在6到64之间'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_]+$/,
                        message: '用户名必须由字母数组和下划线组成'
                    }
                }
            },
            gender: {},
            nickname: {},
            password: {
                notEmpty: {
                    message: '密码不能为空'
                },
                stringLength: {
                    min: 6,
                    max: 32,
                    message: '密码长度在6到64之间'
                },
                regexp:{
                    regexp:/^[a-zA-Z0-9_]{6,32}$/,
                    message: '密码必须由字母数组下划线组成'
                }
            },
            phoneNum: {
                validators: {
                    notEmpty: {
                        message: '手机号码不能为空'
                    },
                    stringLength: {
                        min: 11,
                        max: 11,
                        message: '手机号码必须是11位号码'
                    },
                    regexp: {
                        regexp: /^1[0-9]+$/,
                        message: '手机号码格式错误'
                    }
                }
            },
            email: {
                validators: {
                    notEmpty: {
                        message: '邮箱不能为空'
                    },
                    emailAddress: {
                        message: '邮箱格式错误'
                    }

                }
            },
            description: {},
            birthday: {
                validators: {
                    date: {
                        format:"YYYY-MM-DD",
                        message: "日期格式错误"
                    }
                }
            },
            department: {
                validators: {
                    integer: {
                        message: '部门数据格式错误'
                    }
                }
            },
        }
    }
);