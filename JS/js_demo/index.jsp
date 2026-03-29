<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    
    <select id="state_id">
        <option value="0">Select</option>
    </select>

    <br><br>

    <select id="city_id">
        <option value="0">Select</option>
    </select>

    <script>
        const state_id = document.querySelector('#state_id');
        const city_id = document.querySelector('#city_id');

        const collectStates = async () => {
            const response = await fetch('states.do');
            const result = await response.json();

            return result;
        };

        collectStates().then((data)=>{
            if(data) {
                for(let next of data) {
                    const elm = document.createElement('option');
                    elm.value = next.stateId;
                    elm.innerText = next.state;
                    state_id.append(elm);
                }
            }
        }).catch((err)=>{
            console.log(err);
        });

        const collectCities = async () => {
            const response = await fetch('cities.do?state_id='+state_id.value);
            const result = await response.json();

            return result;
        };

        state_id.addEventListener('change', () => {
            if(state_id.value != 0) {
                collectCities().then((data)=>{
                    if(data) {
                        city_id.innerHTML = '<option value="0">Select</option>';
                        for(let next of data) {
                            const opt = document.createElement('option');
                            opt.value = next.cityId;
                            opt.innerText = next.name;
                            city_id.append(opt);
                        }
                    }
                }).catch((err)=>{
                    console.log(err);
                });
            }
        });
    </script>
</body>
</html>