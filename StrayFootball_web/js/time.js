(function () {
    function Dt(selector) {
        return new Dt.fn.init(selector);
    }

    Dt.fn = Dt.prototype = {
        constructor: Dt,
        initialize: false,
        init: function (selector) {
            if (selector.indexOf('#') > -1) {
                this[0] = document.getElementById(selector.substring(1));
            } else {
                var aryEle = document.querySelectorAll(selector);
                for (var i = 0; i < aryEle.length; i++) {
                    this[i] = aryEle[i];
                }
            }
            return this;
        },
        render: function (opt) {
            /*
              match   hour
              0    1    2    3    4    5    6    7    8    9
              00   01   02   03   04   05   06   07   08   09
              10   11   12   13   14   15   16   17   18   19
              20   21   22   23
              \d
              [01]\d | 2[0-3]
              match   minute   second
              0    1    2    3    4    5    6    7    8    9
              00   01   02   03   04   05   06   07   08   09
              10   11   12   13   14   15   16   17   18   19
              20   ...                                     29
              ...
              50   ...                                     59
              \d
              [0-5]\d
            */
            var regExpDt = /^\d{4}([-/]?)\d{1,2}\1\d{1,2}(?:\s+(?:[01]?\d)|(?:2[0-3])\:[0-5]?\d\:[0-5]?\d)?$/;
            if (Object.prototype.toString.apply(opt.value) == '[object Date]') {
                this.value = opt.value.getFullYear() + '/' + (opt.value.getMonth() + 1) + '/' + opt.value.getDate();
                this.date = opt.value;
                this.initialize = true;
            } else if (Object.prototype.toString.apply(opt.value) == '[object String]') {
                this.value = regExpDt.test(opt.value) ? opt.value : '';
                this.date = new Date(opt.value);
                this.initialize = true;
            } else {
                this.value = '';
                this.date = new Date();
            }
            this.year = this.date.getFullYear();
            this.month = this.date.getMonth() + 1;
            this.day = this.date.getDate();
            this.hour = this.date.getHours();
            this.minute = this.date.getMinutes();
            this.second = this.date.getSeconds();
            this.week = this.date.getDay() == 0 ? 7 : this.date.getDay();
            this.getFirstdateInMonth = function () {
                return new Date(this.date.getFullYear(), this.date.getMonth(), 1);
            }
            this.getCalDate = function (dt, type, num) {
                var dtRtn = new Date();
                dtRtn = dt;
                switch (type) {
                    case 'Y':
                        dtRtn = new Date(dt.getFullYear() + num, dt.getMonth(), dt.getDate());
                        break;
                    case 'M':
                        dtRtn = new Date(dt.getFullYear(), dt.getMonth() + num, dt.getDate());
                        break;
                    case 'D':
                        dtRtn = new Date(dt.getFullYear(), dt.getMonth(), dt.getDate() + num);
                        break;
                    default:
                        break;
                }
                return dtRtn;
            }
            this.getLastdateInMonth = function () {
                var dtRtn = new Date();
                var dtFirstdateInNextmonth = new Date(this.date.getFullYear(), this.date.getMonth() + 1, 1);
                dtRtn = this.getCalDate(dtFirstdateInNextmonth, 'D', -1);
                return dtRtn;
            }
            this.count = this.getLastdateInMonth().getDate();
            this.formatDate = function (type) {
                var strRtn = '',
                    strYear = '',
                    strMonth = '',
                    strDay = '';
                strYear = this.date.getFullYear();
                strMonth = (this.date.getMonth() + 1).toString().length < 2 ? '0' + (this.date.getMonth() + 1) : (this.date.getMonth() + 1);
                strDay = this.date.getDate().toString().length < 2 ? '0' + this.date.getDate() : this.date.getDate();
                switch (type.toString().toUpperCase()) {
                    case 'YYYYMMDD':
                        strRtn = strYear + strMonth + strDay;
                        break;
                    case 'YYYY/MM/DD':
                        strRtn = strYear + '/' + strMonth + '/' + strDay;
                        break;
                    case 'YYYY-MM-DD':
                        strRtn = strYear + '-' + strMonth + '-' + strDay;
                        break;
                    default:
                        strRtn = strYear + '/' + strMonth + '/' + strDay;
                        break;
                }
                return strRtn;
            }
            return this;
        }
    };
    Dt.fn.init.prototype = Dt.fn;

    Dt.fn.extend = Dt.extend = function () {
        var len = arguments.length,
            i = 1,
            j,
            objRtn = arguments[0];
        for (; i < len; i++) {
            for (var j in arguments[i]) {
                objRtn[j] = arguments[i][j];
            }
        }
        return objRtn;
    }

    Dt.fn.create = Dt.create = function () {
        this.render(arguments[0]);
        createDateList.call(this);
    }

    function insertAfter(target, node) {
        //node.style.left = target.offsetLeft + "px";
        if (target.parentNode.children.length == 1) {
            target.parentNode.appendChild(node);
        } else {
            target.parentNode.insertBefore(node, target.nextSibling);
        }
    }

    function setStyle(target, opts) {
        for (var item in opts) {
            target.style[item] = opts[item];
        }
    }

    function createHeader() {
        var me = this;
        var divHeader = document.createElement('div');
        setStyle(divHeader, {
            height: '30px',
            borderBottom: '1px solid silver',
            position: 'relative',
            backgroundColor: 'rgba(214,222,247,1)'
        });
        var btnLastMonth = document.createElement('div');
        btnLastMonth.title = "上月";
        btnLastMonth.onclick = function () {
            var speed_last_month = 1;
            var speed_current_month = 1;
            var b_last_month = false;
            var b_current_month = false;
            var next_month_left = parseInt(me.content.nextMonth.component.style.left);
            var timer = setInterval(function () {
                if (!b_last_month) {
                    if (parseInt(me.Container.children[1].style.left) < 0) {
                        me.Container.children[1].style.left = parseInt(me.Container.children[1].style.left) + Math.floor(5 / speed_last_month) + 'px';
                        speed_last_month += 0.01;
                    } else {
                        b_last_month = !b_last_month;
                    }
                }
                if (!b_current_month) {
                    if (parseInt(me.Container.children[2].style.left) < next_month_left) {
                        me.Container.children[2].style.left = parseInt(me.Container.children[2].style.left) + Math.floor(5 / speed_current_month) + 'px';
                        speed_current_month += 0.01;
                    } else {
                        b_current_month = !b_current_month;
                    }
                }
                if (b_last_month && b_current_month) {
                    clearInterval(timer);
                    me.date = me.getFirstdateInMonth();
                    me.date = me.getCalDate(me.date, 'M', -1);
                    me.render({ value: me.date });
                    var objContent = createContent.call(me);
                    me.Container.removeChild(me.Container.children[3]);
                    me.Container.insertBefore(objContent.lastMonth.component, me.Container.children[0].nextSibling);
                    me.Container.replaceChild(objContent.nextMonth.component, me.Container.children[3]);
                    me.Container.children[2].style.left = 0;
                    me.Container.children[2].style.position = 'absolute';
                    me.Container.children[3].style.left = '250px';
                    me.Container.children[3].style.position = 'relative';
                    me.content.nextMonth = objContent.nextMonth;
                    me.content.nextMonth.left = 250;
                    me.content.currentMonth = me.content.lastMonth;
                    me.content.currentMonth.left = 0;
                    me.content.lastMonth = objContent.lastMonth;
                    me.content.lastMonth.left = -275;
                    me.header.children[2].innerHTML = me.formatDate(me.getFirstdateInMonth());
                    me[0].value = me.formatDate(me.getFirstdateInMonth());
                }
            }, 10);
        }
        setStyle(btnLastMonth, {
            cursor: 'pointer',
            height: '30px',
            border: 0,
            display: 'inline',
            width: '30px',
            position: 'absolute',
            left: 0
        });
        var canvas_last_month = document.createElement('canvas');
        if (canvas_last_month.getContext('2d')) {
            canvas_last_month.height = 30;
            canvas_last_month.width = 30;
            var ctx = canvas_last_month.getContext('2d');
            ctx.beginPath();
            ctx.fillStyle = 'rgba(214,222,247,1)';
            ctx.fillRect(0, 0, 30, 30);
            ctx.strokeStyle = 'rgba(251,251,251,1)';
            ctx.fillStyle = 'rgba(251,251,251,1)';
            ctx.lineWidth = 1;
            ctx.moveTo(7, 0);
            ctx.lineTo(15, 0);
            ctx.lineTo(8, 15);
            ctx.lineTo(15, 30);
            ctx.lineTo(7, 30);
            ctx.lineTo(0, 15);
            ctx.lineTo(7, 0);

            ctx.moveTo(22, 0);
            ctx.lineTo(30, 0);
            ctx.lineTo(23, 15);
            ctx.lineTo(30, 30);
            ctx.lineTo(22, 30);
            ctx.lineTo(15, 15);
            ctx.lineTo(22, 0);
            ctx.fill();
            ctx.stroke();
            btnLastMonth.appendChild(canvas_last_month);
        } else {
            btnLastMonth.innerHTML = "<<";
            setStyle(btnLastMonth, {
                backgroundColor: 'rgba(214,222,247,1)'
            });
        }

        var btnLastDay = document.createElement('div');
        btnLastDay.title = "昨日";
        btnLastDay.onclick = function () {
            if (me.getCalDate(me.date, 'D', -1).getMonth() + 1 == me.month) {
                me.date = me.getCalDate(me.date, 'D', -1);
                me.render({ value: me.date });
                var objContent = createContent.call(me);
                me.Container.replaceChild(objContent.currentMonth.component, me.Container.children[2]);
                me.header.children[2].innerHTML = me.formatDate(me.date);
                me[0].value = me.formatDate(me.date);
            } else {
                var speed_last_month = 1;
                var speed_current_month = 1;
                var b_last_month = false;
                var b_current_month = false;
                var next_month_left = parseInt(me.content.nextMonth.component.style.left);

                me.date = me.getCalDate(me.date, 'D', -1);
                me.render({ value: me.date });
                var objContent = createContent.call(me);
                me.Container.replaceChild(objContent.currentMonth.component, me.Container.children[1]);
                me.Container.children[1].style.left = '-275px';
                var timer = setInterval(function () {
                    if (!b_current_month) {
                        if (parseInt(me.Container.children[2].style.left) < next_month_left) {
                            me.Container.children[2].style.left = parseInt(me.Container.children[2].style.left) + Math.floor(5 / speed_current_month) + 'px';
                            speed_current_month += 0.01;
                        } else {
                            b_current_month = !b_current_month;
                        }
                    }
                    if (!b_last_month) {
                        if (parseInt(me.Container.children[1].style.left) < 0) {
                            me.Container.children[1].style.left = parseInt(me.Container.children[1].style.left) + Math.floor(5 / speed_last_month) + 'px';
                            speed_last_month += 0.01;
                        } else {
                            b_last_month = !b_last_month;
                        }
                    }
                    if (b_last_month && b_current_month) {
                        clearInterval(timer);
                        me.Container.removeChild(me.Container.children[3]);
                        me.Container.insertBefore(objContent.lastMonth.component, me.Container.children[0].nextSibling);
                        me.Container.replaceChild(objContent.nextMonth.component, me.Container.children[3]);
                        me.Container.children[2].style.left = 0;
                        me.Container.children[2].style.position = 'absolute';
                        me.Container.children[3].style.left = '250px';
                        me.Container.children[3].style.position = 'relative';
                        me.content.nextMonth = objContent.nextMonth;
                        me.content.nextMonth.left = 250;
                        me.content.currentMonth = me.content.lastMonth;
                        me.content.currentMonth.left = 0;
                        me.content.lastMonth = objContent.lastMonth;
                        me.content.lastMonth.left = -275;
                        me.header.children[2].innerHTML = me.formatDate(me.getFirstdateInMonth());
                        me[0].value = me.formatDate(me.getFirstdateInMonth());
                    }
                }, 10);
            }
        }
        setStyle(btnLastDay, {
            cursor: 'pointer',
            height: '30px',
            width: '30px',
            display: 'inline',
            position: 'absolute',
            left: '31px'
        });
        var canvas_last_day = document.createElement('canvas');
        if (canvas_last_day.getContext('2d')) {
            canvas_last_day.height = 30;
            canvas_last_day.width = 30;
            var ctx = canvas_last_day.getContext('2d');
            ctx.beginPath();
            ctx.fillStyle = 'rgba(214,222,247,1)';
            ctx.fillRect(0, 0, 30, 30);
            ctx.strokeStyle = 'rgba(251,251,251,1)';
            ctx.fillStyle = 'rgba(251,251,251,1)';
            ctx.lineWidth = 1;
            ctx.moveTo(11, 0);
            ctx.lineTo(19, 0);
            ctx.lineTo(12, 15);
            ctx.lineTo(19, 30);
            ctx.lineTo(11, 30);
            ctx.lineTo(4, 15);
            ctx.lineTo(11, 0);
            ctx.fill();
            ctx.stroke();
            btnLastDay.appendChild(canvas_last_day);
        } else {
            btnLastDay.innerHTML = "<";
            setStyle(btnLastDay, {
                backgroundColor: 'rgba(214,222,247,1)'
            });
        }

        var spanDt = document.createElement('div');
        spanDt.innerHTML = this.formatDate(this.date);
        setStyle(spanDt, {
            position: 'absolute',
            margin: '0 auto',
            top: '5px',
            right: 0,
            bottom: 0,
            left: 0,
            width: '100px'
        });

        var btnTomorrow = document.createElement('div');
        btnTomorrow.title = "明日";
        btnTomorrow.onclick = function () {
            if (me.getCalDate(me.date, 'D', 1).getMonth() + 1 == me.month) {
                me.date = me.getCalDate(me.date, 'D', 1);
                me.render({ value: me.date });
                var objContent = createContent.call(me);
                me.Container.replaceChild(objContent.currentMonth.component, me.Container.children[2]);
                me.Container.children[2].style.left = 0;
                me.header.children[2].innerHTML = me.formatDate(me.date);
                me[0].value = me.formatDate(me.date);
            } else {
                var speed_next_month = 1;
                var speed_current_month = 1;
                var b_next_month = false;
                var b_current_month = false;
                var last_month_left = parseInt(me.content.lastMonth.component.style.left);

                me.date = me.getCalDate(me.date, 'D', 1);
                me.render({ value: me.date });
                var objContent = createContent.call(me);
                me.Container.replaceChild(objContent.currentMonth.component, me.Container.children[3]);
                me.Container.children[3].style.left = '250px';
                var timer = setInterval(function () {
                    if (!b_current_month) {
                        if (parseInt(me.Container.children[2].style.left) > last_month_left) {
                            me.Container.children[2].style.left = parseInt(me.Container.children[2].style.left) - Math.floor(5 / speed_current_month) + 'px';
                            speed_current_month += 0.01;
                        } else {
                            b_current_month = !b_current_month;
                        }
                    }
                    if (!b_next_month) {
                        if (parseInt(me.Container.children[3].style.left) > 0) {
                            me.Container.children[3].style.left = parseInt(me.Container.children[3].style.left) - Math.floor(5 / speed_next_month) + 'px';
                            speed_next_month += 0.01;
                        } else {
                            b_next_month = !b_next_month;
                        }
                    }
                    if (b_next_month && b_current_month) {
                        clearInterval(timer);
                        me.Container.removeChild(me.Container.children[1]);
                        me.Container.insertBefore(objContent.nextMonth.component, me.Container.children[2].nextSibling);
                        me.Container.replaceChild(objContent.lastMonth.component, me.Container.children[1]);
                        me.Container.children[2].style.left = 0;
                        me.Container.children[2].style.position = 'absolute';
                        me.Container.children[3].style.left = '250px';
                        me.Container.children[3].style.position = 'relative';
                        me.content.nextMonth = objContent.nextMonth;
                        me.content.nextMonth.left = 250;
                        me.content.currentMonth = me.content.nextMonth;
                        me.content.currentMonth.left = 0;
                        me.content.lastMonth = objContent.lastMonth;
                        me.content.lastMonth.left = -275;
                        me.header.children[2].innerHTML = me.formatDate(me.getFirstdateInMonth());
                        me[0].value = me.formatDate(me.getFirstdateInMonth());
                    }
                }, 10);
            }
        }
        setStyle(btnTomorrow, {
            cursor: 'pointer',
            height: '30px',
            width: '30px',
            display: 'inline',
            position: 'absolute',
            right: '31px'
        });

        var canvas_tomorrow = document.createElement('canvas');
        if (canvas_tomorrow.getContext('2d')) {
            canvas_tomorrow.height = 30;
            canvas_tomorrow.width = 30;
            var ctx = canvas_tomorrow.getContext('2d');
            ctx.beginPath();
            ctx.fillStyle = 'rgba(214,222,247,1)';
            ctx.fillRect(0, 0, 30, 30);
            ctx.strokeStyle = 'rgba(251,251,251,1)';
            ctx.fillStyle = 'rgba(251,251,251,1)';
            ctx.lineWidth = 1;
            ctx.moveTo(11, 0);
            ctx.lineTo(19, 0);
            ctx.lineTo(26, 15);
            ctx.lineTo(19, 30);
            ctx.lineTo(11, 30);
            ctx.lineTo(18, 15);
            ctx.lineTo(11, 0);
            ctx.fill();
            ctx.stroke();
            btnTomorrow.appendChild(canvas_tomorrow);
        } else {
            btnTomorrow.innerHTML = ">";
            setStyle(btnTomorrow, {
                backgroundColor: 'rgba(214,222,247,1)'
            });
        }

        var btnNextMonth = document.createElement('div');
        btnNextMonth.title = "下月";
        btnNextMonth.onclick = function () {
            var speed_next_month = 1;
            var speed_current_month = 1;
            var b_next_month = false;
            var b_current_month = false;
            var last_month_left = parseInt(me.content.lastMonth.component.style.left);
            var timer = setInterval(function () {
                if (!b_next_month) {
                    if (parseInt(me.Container.children[3].style.left) > 0) {
                        me.Container.children[3].style.left = parseInt(me.Container.children[3].style.left) - Math.floor(5 / speed_next_month) + 'px';
                        speed_next_month += 0.01;
                    } else {
                        b_next_month = !b_next_month;
                    }
                }
                if (!b_current_month) {
                    if (parseInt(me.Container.children[2].style.left) > last_month_left) {
                        me.Container.children[2].style.left = parseInt(me.Container.children[2].style.left) - Math.floor(5 / speed_current_month) + 'px';
                        speed_current_month += 0.01;
                    } else {
                        b_current_month = !b_current_month;
                    }
                }
                if (b_next_month && b_current_month) {
                    clearInterval(timer);
                    me.date = me.getFirstdateInMonth();
                    me.date = me.getCalDate(me.date, 'M', 1);
                    me.render({ value: me.date });
                    var objContent = createContent.call(me);
                    me.Container.removeChild(me.Container.children[1]);
                    me.Container.insertBefore(objContent.nextMonth.component, me.Container.children[2].nextSibling);
                    me.Container.replaceChild(objContent.lastMonth.component, me.Container.children[1]);
                    me.Container.children[1].style.left = '-275px';
                    me.Container.children[1].style.position = 'absolute';
                    me.Container.children[2].style.left = '0';
                    me.Container.children[2].style.position = 'absolute';
                    me.content.lastMonth = objContent.lastMonth;
                    me.content.lastMonth.left = -275;
                    me.content.currentMonth = me.content.nextMonth;
                    me.content.currentMonth.left = 0;
                    me.content.nextMonth = objContent.nextMonth;
                    me.content.nextMonth.left = 250;
                    me.header.children[2].innerHTML = me.formatDate(me.getFirstdateInMonth());
                    me[0].value = me.formatDate(me.getFirstdateInMonth());
                }
            }, 10);
        }
        setStyle(btnNextMonth, {
            cursor: 'pointer',
            height: '30px',
            border: 0,
            display: 'inline',
            width: '30px',
            position: 'absolute',
            right: 0
        });
        var canvas_next_month = document.createElement('canvas');
        if (canvas_next_month.getContext('2d')) {
            canvas_next_month.height = 30;
            canvas_next_month.width = 30;
            var ctx = canvas_next_month.getContext('2d');
            ctx.beginPath();
            ctx.fillStyle = 'rgba(214,222,247,1)';
            ctx.fillRect(0, 0, 30, 30);
            ctx.strokeStyle = 'rgba(251,251,251,1)';
            ctx.fillStyle = 'rgba(251,251,251,1)';
            ctx.lineWidth = 1;
            ctx.moveTo(0, 0);
            ctx.lineTo(8, 0);
            ctx.lineTo(15, 15);
            ctx.lineTo(8, 30);
            ctx.lineTo(0, 30);
            ctx.lineTo(7, 15);
            ctx.lineTo(0, 0);

            ctx.moveTo(15, 0);
            ctx.lineTo(23, 0);
            ctx.lineTo(30, 15);
            ctx.lineTo(23, 30);
            ctx.lineTo(15, 30);
            ctx.lineTo(22, 15);
            ctx.lineTo(15, 0);
            ctx.fill();
            ctx.stroke();
            btnNextMonth.appendChild(canvas_next_month);
        } else {
            btnNextMonth.innerHTML = ">>";
            setStyle(btnNextMonth, {
                backgroundColor: 'rgba(214,222,247,1)'
            });
        }
        divHeader.appendChild(btnLastMonth);
        divHeader.appendChild(btnLastDay);
        divHeader.appendChild(spanDt);
        divHeader.appendChild(btnTomorrow);
        divHeader.appendChild(btnNextMonth);
        return divHeader;
    }

    function createContent() {
        var realDate = this.date;
        var objContent = {
            lastMonth: {
                scope: function () {
                    this.date = this.getFirstdateInMonth();
                    this.date = this.getCalDate(this.date, 'M', -1);
                    this.render({ value: this.date });
                },
                current: true,
                left: -275,
                component: null
            },
            currentMonth: {
                scope: function () {
                    if (this.initialize) {
                        this.date = realDate;
                    } else {
                        this.date = new Date();
                    }
                    this.render({ value: this.date });
                },
                current: true,
                left: 0,
                component: null
            },
            nextMonth: {
                scope: function () {
                    this.date = this.getFirstdateInMonth();
                    this.date = this.getCalDate(this.date, 'M', 1);
                    this.render({ value: this.date });
                },
                current: false,
                left: 250,
                component: null
            }
        };
        for (var item in objContent) {
            objContent[item].scope.call(this);
            objContent[item].year = this.year;
            objContent[item].month = this.month;
            var divContent = document.createElement('div');
            setStyle(divContent, {
                height: '250px',
                position: objContent[item].current ? 'absolute' : 'relative',
                left: objContent[item].left + 'px'
            });
            var tbl = document.createElement('table'),
                row_obj,
                cell_obj,
                row_num = tbl.rows.length,
                cell_num = 0;
            setStyle(tbl, {
                width: '100%'
            });
            row_obj = tbl.insertRow(row_num);
            setStyle(row_obj, {
                backgroundColor: 'rgba(214,222,247,1)'
            });
            row_obj.setAttribute('head', true);
            cell_obj = row_obj.insertCell(cell_num++);
            cell_obj.innerHTML = "一";
            cell_obj.style.width = "35px";
            cell_obj = row_obj.insertCell(cell_num++);
            cell_obj.innerHTML = "二";
            cell_obj.style.width = "35px";
            cell_obj = row_obj.insertCell(cell_num++);
            cell_obj.innerHTML = "三";
            cell_obj.style.width = "35px";
            cell_obj = row_obj.insertCell(cell_num++);
            cell_obj.innerHTML = "四";
            cell_obj.style.width = "35px";
            cell_obj = row_obj.insertCell(cell_num++);
            cell_obj.innerHTML = "五";
            cell_obj.style.width = "35px";
            cell_obj = row_obj.insertCell(cell_num++);
            cell_obj.innerHTML = "六";
            cell_obj.style.width = "35px";
            cell_obj = row_obj.insertCell(cell_num++);
            cell_obj.innerHTML = "日";
            cell_obj.style.width = "35px";
            var dtFirst = this.getFirstdateInMonth();
            var aryDate = [];
            var aryWeekDate = new Array(7);
            for (var i = 0; i < this.count; i++) {
                if (i == 0) {
                    aryWeekDate = new Array(7);
                    aryWeekDate[(dtFirst.getDay() == 0 ? 7 : dtFirst.getDay()) - 1] = dtFirst;
                    var weekNumberForFirstDay = dtFirst.getDay() == 0 ? 7 : dtFirst.getDay();
                    var d = 0;
                    while (weekNumberForFirstDay > 1) {
                        aryWeekDate[weekNumberForFirstDay - 2] = this.getCalDate(dtFirst, 'D', -1 - d);
                        weekNumberForFirstDay--;
                        d++;
                    }
                    if (dtFirst.getDay() == 0) {
                        aryDate.push(aryWeekDate);
                    }
                } else {
                    if (i == this.count - 1 && this.getCalDate(dtFirst, 'D', i).getDay() != 0) {
                        var weekNumberForNextDay = this.getCalDate(dtFirst, 'D', i).getDay();
                        var n = 0;
                        if (weekNumberForNextDay == 1) {
                            aryWeekDate = new Array(7);
                        }
                        while (weekNumberForNextDay < 8) {
                            aryWeekDate[weekNumberForNextDay - 1] = this.getCalDate(dtFirst, 'D', i + n);
                            weekNumberForNextDay++;
                            n++;
                        }
                        aryDate.push(aryWeekDate);
                    }
                    if (this.getCalDate(dtFirst, 'D', i).getDay() == 1) {
                        aryWeekDate = new Array(7);
                        aryWeekDate[0] = this.getCalDate(dtFirst, 'D', i);
                    } else if (this.getCalDate(dtFirst, 'D', i).getDay() == 0) {
                        aryWeekDate[6] = this.getCalDate(dtFirst, 'D', i);
                        aryDate.push(aryWeekDate);
                    } else {
                        aryWeekDate[this.getCalDate(dtFirst, 'D', i).getDay() - 1] = this.getCalDate(dtFirst, 'D', i);
                    }
                }
            }
            for (var j = 0; j < aryDate.length; j++) {
                row_obj = tbl.insertRow(tbl.rows.length);
                row_obj.style.height = '34px';
                cell_num = 0;
                for (var k = 0; k < aryDate[j].length; k++) {
                    cell_obj = row_obj.insertCell(cell_num++);
                    cell_obj.innerHTML = aryDate[j][k] != undefined ? aryDate[j][k].getDate() : '';
                    cell_obj.style.width = "30px";
                    cell_obj.style.cursor = "default";
                    cell_obj.style.borderRadius = '17px';
                    //  set style for today
                    if (aryDate[j][k] != undefined
                        && this.month == new Date().getMonth() + 1
                        && this.year == new Date().getFullYear()
                        && aryDate[j][k].getDate() == new Date().getDate()) {
                        cell_obj.style.border = '1px solid silver';
                    }
                    //  set style for days of other month
                    if (aryDate[j][k] != undefined && this.month != aryDate[j][k].getMonth() + 1) {
                        cell_obj.style.color = 'silver';
                        cell_obj.setAttribute('no-current-month', true);
                        if (aryDate[j][k].getFullYear() < this.year) {
                            cell_obj.setAttribute('last-month', true);
                        } else if (aryDate[j][k].getFullYear() == this.year) {
                            if (aryDate[j][k].getMonth() + 1 < this.month) {
                                cell_obj.setAttribute('last-month', true);
                            } else {
                                cell_obj.setAttribute('next-month', true);
                            }
                        } else {
                            cell_obj.setAttribute('next-month', true);
                        }
                    }
                    if (aryDate[j][k] != undefined && this.year == aryDate[j][k].getFullYear()
                        && this.month == aryDate[j][k].getMonth() + 1
                        && this.day == aryDate[j][k].getDate()) {
                        cell_obj.style.color = 'white';
                        cell_obj.style.backgroundColor = 'rgba(245,169,244,1)';
                        cell_obj.setAttribute('current-date', true);
                    }
                    cell_obj.onmouseover = function () {
                        this.style.backgroundColor = 'rgba(245,169,244,1)';
                        this.style.color = 'white';
                    }
                    cell_obj.onmouseout = function () {
                        if (!this.getAttribute('current-date')) {
                            this.style.backgroundColor = "white";
                            this.style.color = 'black';
                        } else {
                            this.style.color = 'white';
                        }
                        if (this.getAttribute('no-current-month')) {
                            this.style.color = 'silver';
                        }
                    }
                }
            }
            var me = this;
            tbl.onclick = function (e) {
                var e = (e || event.srcElement).target;
                if (e.tagName.toString().toLowerCase() == 'td' && !e.parentNode.getAttribute('head')) {
                    if (!e.getAttribute('no-current-month')) {
                        me.date = new Date(me.date.getFullYear(), me.date.getMonth(), parseInt(e.innerHTML));
                        me.render({ value: me.date });
                        var objContent = createContent.call(me);
                        me.Container.replaceChild(objContent.currentMonth.component, me.Container.children[2]);
                        me.header.children[2].innerHTML = me.formatDate(me.date);
                        me[0].value = me.formatDate(me.date);
                    } else {
                        if (e.getAttribute('last-month')) {
                            var speed_last_month = 1;
                            var speed_current_month = 1;
                            var b_last_month = false;
                            var b_current_month = false;
                            var next_month_left = parseInt(me.content.nextMonth.component.style.left);
                            if (me.date.getMonth() == 0) {
                                me.date = new Date(me.date.getFullYear() - 1, 11, parseInt(e.innerHTML));
                            } else {
                                me.date = new Date(me.date.getFullYear(), me.date.getMonth() - 1, parseInt(e.innerHTML));
                            }
                            me.render({ value: me.date });
                            var objContent = createContent.call(me);
                            me.Container.replaceChild(objContent.currentMonth.component, me.Container.children[1]);
                            me.Container.children[1].style.left = '-275px';
                            var timer = setInterval(function () {
                                if (!b_current_month) {
                                    if (parseInt(me.Container.children[2].style.left) < next_month_left) {
                                        me.Container.children[2].style.left = parseInt(me.Container.children[2].style.left) + Math.floor(5 / speed_current_month) + 'px';
                                        speed_current_month += 0.01;
                                    } else {
                                        b_current_month = !b_current_month;
                                    }
                                }
                                if (!b_last_month) {
                                    if (parseInt(me.Container.children[1].style.left) < 0) {
                                        me.Container.children[1].style.left = parseInt(me.Container.children[1].style.left) + Math.floor(5 / speed_last_month) + 'px';
                                        speed_last_month += 0.01;
                                    } else {
                                        b_last_month = !b_last_month;
                                    }
                                }
                                if (b_last_month && b_current_month) {
                                    clearInterval(timer);
                                    me.Container.removeChild(me.Container.children[3]);
                                    me.Container.insertBefore(objContent.lastMonth.component, me.Container.children[0].nextSibling);
                                    me.Container.replaceChild(objContent.nextMonth.component, me.Container.children[3]);
                                    me.Container.children[2].style.left = 0;
                                    me.Container.children[2].style.position = 'absolute';
                                    me.Container.children[3].style.left = '250px';
                                    me.Container.children[3].style.position = 'relative';
                                    me.content.nextMonth = objContent.nextMonth;
                                    me.content.nextMonth.left = 250;
                                    me.content.currentMonth = me.content.lastMonth;
                                    me.content.currentMonth.left = 0;
                                    me.content.lastMonth = objContent.lastMonth;
                                    me.content.lastMonth.left = -275;
                                    me.header.children[2].innerHTML = me.formatDate(me.getFirstdateInMonth());
                                    me[0].value = me.formatDate(me.getFirstdateInMonth());
                                }
                            }, 10);
                        } else {
                            var speed_next_month = 1;
                            var speed_current_month = 1;
                            var b_next_month = false;
                            var b_current_month = false;
                            var last_month_left = parseInt(me.content.lastMonth.component.style.left);
                            if (me.date.getMonth() == 11) {
                                me.date = new Date(me.date.getFullYear() + 1, 0, parseInt(e.innerHTML));
                            } else {
                                me.date = new Date(me.date.getFullYear(), me.date.getMonth() + 1, parseInt(e.innerHTML));
                            }
                            me.render({ value: me.date });
                            var objContent = createContent.call(me);
                            me.Container.replaceChild(objContent.currentMonth.component, me.Container.children[3]);
                            me.Container.children[3].style.left = '250px';
                            var timer = setInterval(function () {
                                if (!b_current_month) {
                                    if (parseInt(me.Container.children[2].style.left) > last_month_left) {
                                        me.Container.children[2].style.left = parseInt(me.Container.children[2].style.left) - Math.floor(5 / speed_current_month) + 'px';
                                        speed_current_month += 0.01;
                                    } else {
                                        b_current_month = !b_current_month;
                                    }
                                }
                                if (!b_next_month) {
                                    if (parseInt(me.Container.children[3].style.left) > 0) {
                                        me.Container.children[3].style.left = parseInt(me.Container.children[3].style.left) - Math.floor(5 / speed_next_month) + 'px';
                                        speed_next_month += 0.01;
                                    } else {
                                        b_next_month = !b_next_month;
                                    }
                                }
                                if (b_next_month && b_current_month) {
                                    clearInterval(timer);
                                    me.Container.removeChild(me.Container.children[1]);
                                    me.Container.insertBefore(objContent.nextMonth.component, me.Container.children[2].nextSibling);
                                    me.Container.replaceChild(objContent.lastMonth.component, me.Container.children[1]);
                                    me.Container.children[2].style.left = 0;
                                    me.Container.children[2].style.position = 'absolute';
                                    me.Container.children[3].style.left = '250px';
                                    me.Container.children[3].style.position = 'relative';
                                    me.content.nextMonth = objContent.nextMonth;
                                    me.content.nextMonth.left = 250;
                                    me.content.currentMonth = me.content.nextMonth;
                                    me.content.currentMonth.left = 0;
                                    me.content.lastMonth = objContent.lastMonth;
                                    me.content.lastMonth.left = -275;
                                    me.header.children[2].innerHTML = me.formatDate(me.getFirstdateInMonth());
                                    me[0].value = me.formatDate(me.getFirstdateInMonth());
                                }
                            }, 10);
                        }
                    }
                }
            }
            divContent.appendChild(tbl);
            objContent[item].component = divContent;
        }
        //  reset date to current month
        this.date = realDate;
        this.render({ value: this.date });
        return objContent;
    }

    function createFooter() {
        var me = this;
        var divFooter = document.createElement('div');
        setStyle(divFooter, {
            height: '30px',
            width: '100%',
            position: 'absolute',
            bottom: 0
        });
        var divContainer = document.createElement('div');
        setStyle(divContainer, {
            position: 'relative',
            width: '95%',
            height: '30px'
        });
        var btnClose = document.createElement('div');
        setStyle(btnClose, {
            position: 'absolute',
            width: '100%',
            top: 0,
            right: 0,
            bottom: '5px',
            left: 0,
            cursor: 'pointer',
            border: '1px solid silver',
            borderRadius: '4px',
            backgroundColor: 'rgba(214,222,247,1)',
            color: 'white',
            margin: '0 5px'
        });
        btnClose.innerHTML = '关闭';
        btnClose.onclick = function () {
            setStyle(me.Container, {
                display: 'none'
            });
        }
        divContainer.appendChild(btnClose);
        divFooter.appendChild(divContainer);
        return divFooter;
    }

    function createDateList() {
        this.Container = document.createElement('div');
        setStyle(this.Container, {
            position: 'relative',
            marginTop: '5px',
            borderRadius: '3px',
            width: '250px',
            height: '320px',
            border: '1px solid silver',
            //float: 'left',
            textAlign: 'center',
            display: 'none',
            overflow: 'hidden'
        });

        this.header = createHeader.call(this);
        this.content = createContent.call(this);
        this.footer = createFooter.call(this);
        this.Container.appendChild(this.header);
        for (var item in this.content) {
            this.Container.appendChild(this.content[item].component);
        }
        this.initialize = true;
        this.Container.appendChild(this.footer);
        insertAfter(this[0], this.Container);
    }

    Dt.fn.choose = function (options) {
        options = options || {};
        var defaults = {
            value: new Date()
        };
        var opt = this.extend({}, defaults, options);
        this.create(opt);
        this[0].onfocus = function () {
            this.parentNode.children[1].style.display = '';
        }
    }

    var $ = function (selector) {
        return Dt(selector);
    }

    window.$ = $;
})()

window.onload = function () {
    $('#txtDt').choose();
}
