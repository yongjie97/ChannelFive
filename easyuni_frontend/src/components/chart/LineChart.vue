<template>
    <Line :chart-data="chartData" :chart-options="chartOptions" />
</template>

<script>
import { Line } from 'vue-chartjs'

import {
    Chart as ChartJS, 
    Title, 
    Tooltip, 
    Legend, 
    LineElement, 
    CategoryScale, 
    LinearScale,
    PointElement,
    Filler
} from 'chart.js'

ChartJS.register(
    Title, 
    Tooltip, 
    Legend, 
    PointElement,
    LineElement, 
    CategoryScale, 
    LinearScale,
    Filler,
)

export default {
    name: 'LineChart',
    components: { Line },
    props: {
        chartData: {
            type: Object,
            required: true,
        },
        chartOptions: {
            type: Object,
            default: () => {},
        }
    },
}
</script>
