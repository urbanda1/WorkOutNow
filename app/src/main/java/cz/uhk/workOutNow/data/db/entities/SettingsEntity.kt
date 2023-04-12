package cz.uhk.workOutNow.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

//settings - systémová nastavení, která se uchovájí v databazí, zatím tam bude jen option pro notifikace
@Entity
data class SettingsEntity(

    @PrimaryKey(autoGenerate = false)
    var settingsID: Int,

    //nastavení posílání notifikací, když uživatel skončí trénink - 1 je pro true a 0 pro false
    var notificationsSettings: Int

)