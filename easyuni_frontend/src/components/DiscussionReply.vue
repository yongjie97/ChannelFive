<template>
    <div class="card mb-3">
      <div class="card-body py-3 px-4">
        <p class="small mb-2 text-muted">{{ displayName }}<span class="mx-2">|</span>{{ date }}</p>
        <p class="mb-1" v-html="message"></p>
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

  </style>