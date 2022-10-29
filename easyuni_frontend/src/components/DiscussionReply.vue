<template>
    <div class="card p-3" :class="{ 'bg-custom': index%2==1 }">
      <div class="card-body pb-0 px-4">
        <p class="small mb-3 text-muted">{{ displayName }}<span class="mx-2">|</span>{{ date }}</p>
        <p v-html="message"></p>
      </div>
    </div>
  </template>
  
  <script>
  
  import moment from 'moment';
  
  export default {
    name: 'DiscussionReply',
    props: {
      title: String,
      message: String,
      displayName: String,
      date: String,
      index: Number,
      size: Number
    },
    computed: {
      date() {
        return moment(this.date).format('lll')
      }
    }
  }
  </script>
  
  <style scoped>
  a {
    color: inherit;
    font-family: inherit;
    font-style: inherit;
    font-weight: inherit;
    text-decoration: none;
  }
  
  a:hover {
    color: #ffc107;
  }
  
  .card {
    border-radius: 0;
  }

  .bg-custom {
    border: 1px;
    background-color: #e9e7e7;
  }

  </style>