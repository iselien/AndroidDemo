package io.yovelas;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "trophy",
        foreignKeys = {
                @ForeignKey(
                        entity = User.class,
                        parentColumns = "id",
                        childColumns = "userId",
                        onDelete = ForeignKey.CASCADE
                )},
        indices = { @Index(value = "id")}
)
public class Trophy {

    @PrimaryKey(autoGenerate = true)
    long id;

    public long userId;

    String description;

    public Trophy(long userId, String description) {
        this.userId = userId;
        this.description = description;
    }
}

