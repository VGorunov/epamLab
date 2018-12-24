'use strict';
const tasksNode = document.getElementById('bodyTasks');
const todayTasks = document.getElementById('todayTasks');
const tomorrowTasks = document.getElementById('tomorrowTasks');
const somedayTasks = document.getElementById('somedayTasks');
const deleteTasks = document.getElementById('delete');
const recycleBin = document.getElementById('recycleBin');
const fixedTasks = document.getElementById('fixed');
const executedTasks = document.getElementById('executed');
const restoreTasks = document.getElementById('restore');
const destroyTasks = document.getElementById('destroy');

const getTableRow = (item) => {
    if (!item) {
        return;
    }
    const isFileAttached = !!item.actualFileName;
    return `
        <tr>
            <td><input type="checkbox" name="\${item.taskId}"></td>
            <td>\${item.description}</td>
            <td>\${item.dateCompletion}</td>
            <td>            
                <a href="\${item.actualFileName}" >\${item.fileName || ''}</a>
                <input type="file" data-choose-file="\${item.taskId}">
                <input type="button" class="upload-file-button" data-upload-file="\${item.taskId}" value="Upload file">
                <input type="button" class="delete-file-button" data-delete-file="\${item.taskId}" data-has-file="\${isFileAttached}" value="Delete file">
            </td>
        </tr>
    `;
};

const loadToTemplate = (node, data) => {
    if(!Array.isArray(data) || !node) {
        return;
    }
    node.innerHTML = data.map(item => getTableRow(item));
};

const uploadFile = (taskId) => {
    if (!taskId) {
        return Promise.reject();
    }
    const fileInput = document.querySelector(`input[type="file"][data-choose-file="\${taskId}"]`);
    if (!fileInput || !fileInput.files[0]) {
        alert(`No file chosen for task `);
        return Promise.resolve();
    }

    const formData = new FormData();

    formData.append('file', fileInput.files[0]);
    formData.append('taskId', taskId);

    return fetch('/todo/upload', {
        method: 'POST',
        body: formData,
    })
};

const deleteFile = (taskId, hasFile) => {
    if (!taskId) {
        return Promise.reject();
    }
    const fileInput = document.querySelector(`input[type="file"][data-choose-file="\${taskId}"]`);
    if (!hasFile) {
        alert(`You don't have attached file for task`);
        return Promise.resolve();
    }

    const formData = new FormData();

    formData.append('taskId', taskId);

    return fetch('/todo/upload', {
        method: 'DELETE',
        body: formData,
    });
};

const addUploadFileListeners = () => {
    Array.from(document.querySelectorAll('.upload-file-button'))
        .forEach(fileButton => {
            fileButton.addEventListener('click', event => {
                uploadFile(event.target.dataset.uploadFile)
                    .then(() => {
                        // Load new data from server
                        loadData('todo/tasks?section=ALLTASKS');
                    })
            });
        });
};

const addDeleteFileListeners = () => {
    Array.from(document.querySelectorAll('.delete-file-button'))
        .forEach(fileButton => {
            fileButton.addEventListener('click', event => {
                const hasFileOnInput = event.target.dataset.hasFile === 'true';
                deleteFile(event.target.dataset.deleteFile, hasFileOnInput)
                    .then(() => {
                        // Load new data from server
                        loadData('todo/tasks?section=ALLTASKS');
                    })
            });
        });
};

const loadData = url => fetch(url)
        .then(res => res.json())
        .then(data => {
            loadToTemplate(tasksNode, data);
            return data;
        })
        .then(() => {
            addUploadFileListeners();
            addDeleteFileListeners();
        });


function    deleteData(getTaskId){
    return fetch('todo/delete', {
        method: 'POST',
        headers: {
            "Content-type" : 'application/json'
        },
        body: JSON.stringify(getTaskId)
    })
        .then(function (response) {
            console.log("Delete");
        })
        .then(() => {
            // Load new data from server
            loadData('todo/tasks?section=ALLTASKS');
        })

}

function executedData(getTaskId) {
    return fetch('todo/executed', {
        method: 'POST',
        headers: {
            "Content-type" : 'application/json'
        },
        body: JSON.stringify(getTaskId)
        })
        .then(function (response) {
            console.log("Execute");
        })
        .then(() => {
            // Load new data from server
            loadData('todo/tasks?section=ALLTASKS');
        })
}

function restoreData(getTaskId) {
    return fetch('todo/restore', {
        method: 'POST',
        headers: {
            "Content-type" : 'application/json'
        },
        body: JSON.stringify(getTaskId)
    })
        .then(function (response) {
            console.log("Restore");
        }).then(() => {
            // Load new data from server
            loadData('todo/tasks?section=ALLTASKS');
        })
}

function destroyData(getTaskId) {
    return fetch('todo/destroy', {
        method: 'POST',
        headers: {
            "Content-type" : 'application/json'
        },
        body: JSON.stringify(getTaskId)
    })
        .then(function (response) {
            console.log("Destroy");
        })
        .then(() => {
            // Load new data from server
            loadData('todo/tasks?section=RECYCLE_BIN');
        })
}

function getTaskId() {
    return [...document.querySelectorAll('input[type="checkbox"]')]
        .filter((elem = {}) => elem.checked)
        .map((elem = {}) => elem.name);
}

document.addEventListener('DOMContentLoaded', function () {
    loadData('todo/tasks?section=ALLTASKS');
        restoreTasks.style = "display:none";
        destroyTasks.style = "display:none";
        deleteTasks.style = "display:inline";
        executedTasks.style = "display: inline";

});

todayTasks.addEventListener('click', function () {
    loadData('todo/tasks?section=TODAY');
    restoreTasks.style = "display:none";
    destroyTasks.style = "display:none";
    deleteTasks.style = "display:inline";
    executedTasks.style = "display: inline";
});

tomorrowTasks.addEventListener('click', function () {
    loadData('todo/tasks?section=TOMORROW');
    restoreTasks.style = "display:none";
    destroyTasks.style = "display:none";
    deleteTasks.style = "display:inline";
    executedTasks.style = "display: inline";
});

somedayTasks.addEventListener('click', function () {
    loadData('todo/tasks?section=SOMEDAY');
    restoreTasks.style = "display:none";
    destroyTasks.style = "display:none";
    deleteTasks.style = "display:inline";
    executedTasks.style = "display: inline";

});

recycleBin.addEventListener('click', function () {
    loadData('todo/tasks?section=RECYCLE_BIN');
        deleteTasks.style = "display:none";
        executedTasks.style = "display:none";
        restoreTasks.style = "display:inline";
        destroyTasks.style = "display:inline";
});

fixedTasks.addEventListener('click', function () {
    loadData('todo/tasks?section=FIXED');
    deleteTasks.style = "display:inline";
    executedTasks.style = "display:none";
    restoreTasks.style = "display:none";
    destroyTasks.style = "display:none";
});

deleteTasks.addEventListener('click', function () {
    deleteData(getTaskId());
});

executedTasks.addEventListener('click', function () {
    executedData(getTaskId());
});

restoreTasks.addEventListener('click', function () {
    restoreData(getTaskId());
});

destroyTasks.addEventListener('click', function () {
    destroyData(getTaskId());
});

