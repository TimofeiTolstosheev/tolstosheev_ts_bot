theme: /EquipmentReturn
    state: Замена оборудования, пульта
        q!: $240_EquipmentReturn
        intent!: /240_EquipmentReturn
        script:
            startIntent('240_EquipmentReturn');
        go!: /Transfer/Перевод чата