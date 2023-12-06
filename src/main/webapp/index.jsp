<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<html>
<body bgcolor="#f5f5dc">
<div style="background-color: aliceblue">
    <form action="hello" method="POST">
        <center>
            <div class="field">
                <label for="txt1">Введите название файла для открытия:</label>

                <select name="p1">
                    <option value="поэзия">Поэзия</option>
                    <option value="наука">Наука</option>
                    <option value="проза">Проза</option>
                    <option value="фантастика">Фантастика</option>
                </select>
            </div>
            <br>
            <div class="field">
                <label for="txt2">Размер шрифта</label>
                <input type="number" name="p2" id="txt5" value="">
            </div>
            <br>
            <input type="submit" value="Отправить">
        </center>
    </form>
</div>
</body>
</html>

