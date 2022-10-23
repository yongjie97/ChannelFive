<template>
    <div class="card pt-4" :class="{ 'bg-custom': index%2==1 }">
      <div class="card-body pb-0 px-4">
        <p class="small mb-2 text-muted">{{ displayName }}<span class="mx-2">|</span>{{ date }}</p>
        <p class="pb-2" v-html="message"></p>
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
    background-color: #f0f0f0
  }

  </style>