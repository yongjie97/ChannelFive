<template>
    <div class="py-3">
        <h3 class="mb-0">IGP Course Intake</h3>
        <small class="text-muted text-small">Data provided by <a href="https://data.gov.sg/" target="_blank">Data.gov.sg</a></small>
        <LineChart :chartData="chartData" :chartOptions="chartOptions" />
    </div>
</template>

<script>
import LineChart from './LineChart'

export default {
    name: 'IntakeChart',
    components: {
        LineChart,
    },
    props: {
        chartData: {
            type: Object,
            required: true,
        },
    },
    data() {
        return {
            chartOptions: {
                maintainAspectRatio: false,
                responsive: true,
                plugins: {
                    legend: {
                        display: false
                    },
                },
                fill: true,
                backgroundColor: 'rgba(0,63,92,0.5)',
                tension: 0.4,
                pointBorderWidth: 2,
                borderColor: "rgba(0,63,92,1)",
                pointBackgroundColor: "rgba(255, 255, 255, 1)",
                pointRadius: 5,
                pointHoverRadius: 10,
                pointHoverBackgroundColor: "rgba(255, 255, 255, 0)",
                scales: {
                    y: {
                        offset: true,
                        suggestedMin: 2500,
                        grid: {
                            display: true,
                        },
                    },
                    x: {
                        grid: {
                            display: false,
                        },
                    }
                }
            },
        }
    },
}
</script>