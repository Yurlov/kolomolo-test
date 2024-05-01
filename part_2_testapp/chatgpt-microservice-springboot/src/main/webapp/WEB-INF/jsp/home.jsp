<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<body>
<h1>ChatGPT</h1>

<form id="chatForm">
    <label for="question">Question:</label>
    <input type="text" id="question" name="question">
    <button type="submit">Send</button>
</form>
<div id="chatResponse"></div>

<hr>

<form id="transcriptionForm" enctype="multipart/form-data">
    <label for="file">File:</label>
    <input type="file" id="file" name="file">
    <button type="submit">Upload</button>
</form>
<div id="transcriptionResponse"></div>

<script>
    document.getElementById('chatForm').addEventListener('submit', function(event) {
        event.preventDefault();

        var question = document.getElementById('question').value;
        var xhr = new XMLHttpRequest();
        xhr.open('POST', '/api/v1/chat');
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.onload = function() {
            var response = JSON.parse(xhr.responseText);
            if (xhr.status === 200) {
                console.log(response);
                document.getElementById('chatResponse').innerHTML = '<p>' + response.choices[0].message.content +'</p>';
            } else {
                document.getElementById('chatResponse').innerHTML = '<p>Error: ' + response.error.message + '</p>';
            }
        };
        xhr.send(JSON.stringify({ question: question }));
    });

    document.getElementById('transcriptionForm').addEventListener('submit', function(event) {
        event.preventDefault();

        var formData = new FormData();
        formData.append('file', document.getElementById('file').files[0]);
        var xhr = new XMLHttpRequest();
        xhr.open('POST', '/api/v1/transcription');
        xhr.onload = function() {
            var response = JSON.parse(xhr.responseText);
            if (xhr.status === 200) {
                document.getElementById('transcriptionResponse').innerHTML = '<p>' + response.text + '</p>';
            } else {
                document.getElementById('transcriptionResponse').innerHTML = '<p>Error: ' + response.error.message + '</p>';
            }
        };
        xhr.send(formData);
    });
</script>
</body>
</html>