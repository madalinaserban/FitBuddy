package com.example.fitbuddyapp.ui.exercises

import com.example.fitbuddyapp.R

object ExerciseObject {
    public var exercisesList= mutableListOf<Exercises>()

    init {
        exercisesList.add(Exercises("Genoflexiuni",20,"de repetari", R.drawable.genoflexiuni,"1"))
        exercisesList.add(Exercises("Flotari",10,"(de) repetari", R.drawable.flotari,"1"))
        exercisesList.add(Exercises("Sarituri",10," (de) repetari", R.drawable.sarituri,"2"))
        exercisesList.add(Exercises("Flexii abdominale",12,"(de) repetari", R.drawable.flexiiabdominale,"1"))
        exercisesList.add(Exercises("Alergare",10," (de) minute", R.drawable.runner,"2"))
        exercisesList.add(Exercises("Ridicari de greutati",20,"de repetari", R.drawable.greutati,"1"))
        exercisesList.add(Exercises("Genoflexiuni cu saritura",10," (de) repetari", R.drawable.genoflexiunicusaritura,"3"))
        exercisesList.add(Exercises("Ridicari",10,"(de) repetari", R.drawable.ridicari,"3"))
        exercisesList.add(Exercises("Ridicari de brate",12,"(de) repetari", R.drawable.ridicaridebrate,"1"))
        exercisesList.add(Exercises("Fandari inapoi",10,"(de) repetari", R.drawable.lunges,"1"))
        exercisesList.add(Exercises("Scaunul",10,"(de) repetari", R.drawable.scaunul,"2"))
        exercisesList.add(Exercises("Ridcari de corp",20," de repetari", R.drawable.cardio,"1"))
        exercisesList.add(Exercises("Scandura",20," de repetari", R.drawable.scanduradreapta,"1"))
        exercisesList.add(Exercises("Ridicari de bazin",45,"de secunde", R.drawable.scandura,"2"))
        exercisesList.add(Exercises("Fandari laterale",20,"de repetari", R.drawable.fandarilaterale,"1"))
        exercisesList.add(Exercises("Plank",45," de secunde", R.drawable.plank,"1"))

    }

    fun getTitles():List<String>{
        val title= mutableListOf<String>()
        for (signs in exercisesList) {
            title.add(signs.title)
        }
        return title
    }
    fun getNumbers():List<Int>{
        val numbers= mutableListOf<Int>()
        for (exercise in exercisesList) {
            numbers.add(exercise.number)
        }
        return numbers
    }

    fun getPredictions():List<String>{
        val prediction= mutableListOf<String>()
        for(sign in exercisesList){
            prediction.add(sign.difficulty)
        }
        return prediction
    }

    fun getTitle(position:Int): String {
       return exercisesList[position].title
    }
    fun getNumber(position: Int):Int{
        return exercisesList[position].number
    }

    fun getDetail(position:Int): String {
        return exercisesList[position].detail
    }

    fun getImage(position: Int):Int{
        return exercisesList[position].image
    }

    fun getDifficulty(position: Int):String{
        return exercisesList[position].difficulty
    }

    fun getDetails():List<String>{
        val details= mutableListOf<String>()
        for(signs in exercisesList){
            details.add(signs.detail)
        }
        return details
    }

    fun getImages():List<Int>{
        val images= mutableListOf<Int>()
        for(signs in exercisesList){
            images.add(signs.image)
        }
        return images
    }

    fun getSign(index:Int): Exercises{
        return exercisesList[index]
    }
     fun getSignSize():Int
    {
         return exercisesList.size-1
     }

    fun setDifficulty(index:Int, difficult:String){
        exercisesList[index].difficulty=difficult
    }
}

