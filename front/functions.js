

let currentActivityId = null;
const API_BASE = 'http://localhost:8080/api/activities';

function showSection(sectionId) {
    document.querySelectorAll('.content-section').forEach(s => s.classList.remove('active'));
    document.getElementById(sectionId).classList.add('active');
    document.querySelectorAll('.tab').forEach(t => t.classList.remove('active'));
    event.target.classList.add('active');
}

async function loadInitialData() {
    try {
        const [activities, people] = await Promise.all([
            axios.get(API_BASE),
            axios.get('http://localhost:8080/api/people')
        ]);

        renderActivities(activities.data);
        renderPeopleSelect(people.data);
        initializeCalendar(activities.data);
    } catch (error) {
        console.error('Erro ao carregar dados:', error);
    }
}

function renderActivities(activities) {
    
    const container = document.getElementById('activityList');
    container.innerHTML = activities.map(activity => `
        
        <div class="activity-item">
            <div>
                <h3>${activity.title}</h3>
                <p>${dateFns.format(new Date(activity.startDate), 'dd/MM/yyyy HH:mm')}</p>
            </div>
            <div>
                <button onclick="editActivity(${activity.id})">Editar</button>
                <button onclick="deleteActivity(${activity.id})">Excluir</button>
            </div>
        </div>     
        
    `).join('');

}

document.getElementById('activityForm').addEventListener('submit', async (e) => {
    e.preventDefault();

    const activityData = {
        title: document.getElementById("title").value,
        startDate: document.getElementById("startDate").value,
        peopleIds: Array.from(document.getElementById("people").selectedOptions).map(option => option.value)
    };

    try {
        if (currentActivityId) {
            await axios.put(`${API_BASE}/activities/${currentActivityId}`, activityData);
        } else {
            await axios.post(`${API_BASE}/activities`, activityData);
        }
        await loadInitialData();
        resetForm();
    } catch (error) {
        console.error('Error saving activity:', error);
    }
});

function initializeCalendar(activities) {
    const calendarEl = document.getElementById('calendar');
    const calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        events: activities.map(a => ({
            title: a.title,
            start: a.startDate
        }))
    });
    calendar.render();
}

function resetForm() {
    document.getElementById('activityForm').reset();
    currentActivityId = null;
}

async function editActivity(id) {
    try {
        const response = await axios.get(`${API_BASE}/${id}`);
        const activity = response.data;

        document.getElementById('title').value = activity.title;
        document.getElementById('startDate').value = activity.startDate.replace(' ', 'T');
        
        currentActivityId = id;
    } catch (error) {
        console.error('Erro ao carregar atividade:', error);
    }
}

async function deleteActivity(id) {
    if (confirm('Tem certeza que deseja excluir esta atividade?')) {
        try {
            await axios.delete(`${API_BASE}/activities/${id}`);
            await loadInitialData();
        } catch (error) {
            console.error('Error deleting activity:', error);
        }
    }
}

async function loadPeople() {
    try {
        const response = await axios.get('http://localhost:8080/api/people');
        const select = document.getElementById('people');
        select.innerHTML = response.data.map(person => `
            <option value="${person.id}">${person.name}</option>
        `).join('');
    } catch (error) {
        console.error('Erro ao carregar pessoas:', error);
    }
}


document.addEventListener('DOMContentLoaded', loadInitialData);
