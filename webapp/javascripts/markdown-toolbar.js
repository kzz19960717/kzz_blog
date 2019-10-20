/* 描述编辑/预览状态 */
var $is_previewing = false;
/* 编辑区 */
var $editor = document.getElementById('editor');
/* 工具项标识前缀(pre) 标识：.pre+name */
var $item_name_pre = "add-";

/* 工具项标题枚举 */
var Item = {
    Title1: "强调",
    Title2: "斜体",
    Title3: "标题",
    Title4: "引用",
    Title5: "无序列表",
    Title6: "有序列表",
    Title7: "代码",
    Title8: "链接",
    Title9: "图片",
    Title10: "表情"
};


/* 工具项 { name: 标签名 用于识别, title:用于提示, offset_start: 光标起始偏移量, offset_end： 光标结束偏移量, markdown: md格式字符 } */
var $items = [{ name: "bold", title: Item.Title1, offset_start: -4, offset_end: -2, markdown: ("**" + Item.Title1 + "**") }, //
    { name: "italic", title: Item.Title2, offset_start: -4, offset_end: -2, markdown: (" _" + Item.Title2 + "_ ") }, //
    { name: "header", title: Item.Title3, offset_start: -2, offset_end: 0, markdown: ("### " + Item.Title3) }, //
    1, //   分隔线
    { name: "quote-left", title: Item.Title4, offset_start: -8, offset_end: 0, markdown: ("> 这里输入引用文本") }, //
    { name: "list-ul", title: Item.Title5, offset_start: -13, offset_end: -6, markdown: ("- 这里是列表文本\n- \n- ") }, //
    { name: "list-ol", title: Item.Title6, offset_start: -15, offset_end: -8, markdown: ("1. 这里是列表文本\n2. \n3. ") }, //
    { name: "code", title: Item.Title7, offset_start: -10, offset_end: -4, markdown: ("```\n这里输入代码\n```") }, //
    1, //   分隔线
    { name: "link", title: Item.Title8, offset_start: -16, offset_end: -10, markdown: ("[输入链接说明](http://)") }, //
    { name: "image", title: Item.Title9, offset_start: -16, offset_end: -10, markdown: ("![输入图片说明](http://)") }, //
    { name: "smile-o", title: Item.Title10, is_plugin: true, id: 0, type: "emoji" }];


/* 工具项事件处理 */
/**
 * 编辑框光标后插字符
 *  $editor : 编辑框
 *  $insert_str: 插入的字符
 *  $offset_start: 插入字符后光标的起始偏移量 (插入前光标+插入字符长度+偏移量) 
 *  $offset_end: 插入字符后光标的终止偏移量
 */
function insert_at_cursor($editor, $insert_str, $offset_start, $offset_end) {
    //IE 支持
    if (document.selection) {
        $editor.focus();
        var selection = "";
        sel = document.selection.createRange();
        selection = sel.text;
        sel.text = $insert_str;
        sel.moveStart("character", $offset_start);
        sel.moveEnd("character", $offset_end);
        sel.select();
        if (selection != "") {
            sel.text = selection;
        }
    } else if ($editor.selectionStart || $editor.selectionStart == '0') { //Firefox/Chrome 等支持
        var selection = "";
        var startPos = $editor.selectionStart;
        var endPos = $editor.selectionEnd;
        if (startPos != endPos) { //已选中字符
            selection = $editor.value.substring(startPos, endPos);
        }
        $editor.value = $editor.value.substring(0, startPos) + $insert_str + $editor.value.substring(endPos, $editor.value.length);
        $editor.selectionStart = startPos + $insert_str.length + $offset_start;
        $editor.selectionEnd = startPos + $insert_str.length + $offset_end;
        // 再进行选中字符替换
        if (selection != "") {
            $editor.value = $editor.value.substring(0, $editor.selectionStart) + selection + $editor.value.substring($editor.selectionEnd, $editor.value.length);
        }
    } else {
        $editor.value += $insert_str; //直接在编辑文本末插入字符
    }
}

/* 工具项点击处理公有部分 */
function toolbar_item_on_click($insert_str, $offset_start, $offset_end) {
    if ($is_previewing) return;
    // 当前不处于编辑区，则切换到编辑区
    if (!$("#edit-tabs .edit-node").is(".edit-tab-border-deepen")) {
        $("#edit-tabs .edit-node").click();
    }
    //插入值
    insert_at_cursor($editor, $insert_str, $offset_start, $offset_end);
    return false;
}

/* 绑定工具项点击事件 */
function bind_item_on_click($item) {
    var classname = "." + $item_name_pre + $item.name;
    $(".markdown-toolbar").on("click", classname, function() {
        toolbar_item_on_click($item.markdown, $item.offset_start, $item.offset_end);
    });
}

/* 绑定表情选择工具项事件 */
function bind_item_emoji_picker_on_click($item) {
    var classname = "." + $item_name_pre + $item.name;
    $(".markdown-toolbar").on("click", classname, function() {
        $(".emoji-menu-tab a").eq(0).click();
    });
}

/* 构建工具项 */
function build_a_item($item) {
    return "<a class='" + "toolbar-item " + $item_name_pre + $item.name + "' " + //
        "href='#' " + "title='" + $item.title + "'>" + //
        "     <i class='fa fa-" + $item.name + "'></i>" + //
        "</a>";
}

/* 构建表情选择项 */
function build_a_item_emoji_picker($item) {
    return "<a class=' emoji-picker-btn " + "toolbar-item " + $item_name_pre + $item.name + "' " + //
        "href='#' " + "title='" + $item.title + "' data-toggle='dropdown'>" + //
        "     <i class='fa fa-" + $item.name + "'></i>" + //
        "</a>";
}

/* 动态构建工具栏 */
function build_toolbar() {
    var $toolbar = "";
    for (i = 0; i < $items.length; i++) {
        if ($items[i] == 1) { // 添加分隔线
            $toolbar += "<span class='divider'></span>";
        } else if (!$items[i].is_plugin) {
            $toolbar += build_a_item($items[i]);
            // 绑定点击处理
            bind_item_on_click($items[i]);
        } else {
            //非markdown插件工具项单独绑定事件,如表情包等
            $toolbar += build_a_item_emoji_picker($items[i]);
            bind_item_emoji_picker_on_click($items[i]);
        }
    }
    return $toolbar;
}

/* 注册事件 */
function bind_toolbar() {
    for (i = 0; i < $items.length; i++) {
        if ($items[i] != 1) {
            if (!$items[i].is_plugin) {
                // 绑定点击处理
                bind_item_on_click($items[i]);
            } else {
                //非markdown插件工具项单独绑定事件,如表情包等
                bind_item_emoji_picker_on_click($items[i]);
            }
        }
    }
}

$(function() {
    // 不使用javascripts添加工具栏
    // $(".markdown-toolbar").append(build_toolbar()); 
    bind_toolbar();
});
