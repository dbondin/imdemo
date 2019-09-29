function getIndex(list, id) {
    for (var i = 0; i < list.length; i++ ) {
        if (list[i].id === id) {
            return i;
        }
    }

    return -1;
}


var messageApi = Vue.resource('/message{/id}');
var userApi = Vue.resource('/user/all');

Vue.component('message-form', {
    props: ['user', 'messages', 'messageAttr'],
    data: function() {
        return {

            text: '',
            id: '',
            userId: ''
        }
    },
    watch: {
        messageAttr: function(newVal, oldVal) {
            this.text = newVal.text;
            this.id = newVal.id;
            this.userId = newVal.userId;
        }
    },
    template:
        '<div>' +
        '<input type="text" placeholder="Write something" v-model="text" />' +
        '<input type="button" value="Save" @click="save" />' +
        '</div>',
    methods: {
        save: function() {
            var message = { 'userId': this.userId, 'text': this.text }; //FIXME Сделать динамическую подстановку id

            // залезть в список пользователей на UI, взять выбраного пользователя,
            // подставить его в userId
            
            if (this.id) {
                messageApi.update({id: this.id}, message).then(result =>
                    result.json().then(data => {
                        var index = getIndex(this.messages, data.id);
                        this.messages.splice(index, 1, data);
                        this.text = ''
                        this.id = ''
                    })
                )
            } else {
                messageApi.save({}, message).then(result =>
                    result.json().then(data => {
                        this.messages.push(data);
                        this.text = ''
                    })
                )
            }
        }
    }
});

Vue.component('message-row', {
    props: ['message', 'editMethod', 'messages'],
    template: '<div>' +
        '<i>({{ message.id }})</i> {{ message.text }}' +
        // '<span style="position: absolute; right: 0">' +
        // '<input type="button" value="Edit" @click="edit" />' +
        // '<input type="button" value="X" @click="del" />' +
        // '</span>' +
        '</div>',
    // methods: {
    //     edit: function() {
    //         this.editMethod(this.message);
    //     },
    //     del: function() {
    //         messageApi.remove({id: this.message.id}).then(result => {
    //             if (result.ok) {
    //                 this.messages.splice(this.messages.indexOf(this.message), 1)
    //             }
    //         })
    //     }
    // }
});

Vue.component('messages-list', {
    props: ['messages'],
    data: function() {
        return {
            message: null
        }
    },
    template:
        '<div style="position: relative; width: 300px;">' +
        '<message-form :messages="messages" :messageAttr="message" />' +
        '<message-row v-for="message in messages" :key="message.id" :message="message" ' +
        ':editMethod="editMethod" :messages="messages" />' +
        '</div>',
    methods: {
        editMethod: function(message) {
            this.message = message;
        }
    }
});

Vue.component('users-list', {
    props: ['users'],
    template: '<div>'+
                '<label>User: </label>' +
                        '<select>' +
                            '<option  v-for="user in users" v-bind:key="user.id">' +
                                '({{user.id}}) {{user.username}}' +
                            '</option>' +
                        '</select>'+
               '</div>',

    created: function () {
        userApi.get().then(result =>
            result.json().then(data =>
                data.forEach(user => this.users.push(user))
            )
        )
    }
});

var app = new Vue({
    el: '#app',
    template:
        '<div>' +
        '<div v-if="!profile"> Need to authorize by <a href="/login">Google</a></div>' +
        '<v-else'+
        '<div>{{profile.name}}&nbsp;<a href="/logout">Exit</a></div>' +
        '<messages-list :messages="messages" />' +
        '<users-list :users = "users"/>' +
        '</div>' +
        '</div>',
    data: {
        messages: [],
        users:[],
        profile: {'name': 'test'}, //FIXME Сделать динамическую подстановку имени
        user: ""//frontendData.profile
    },
    createdMessage: function() {
    	messageApi.get({}).then(result =>
        	result.json().then(data => {
        		for(var m in data) {
        			console.log(data[m]);
        			this.messages.push(data[m]);
        		}
        		this.text = ''
        	})
    	)
    },
});
