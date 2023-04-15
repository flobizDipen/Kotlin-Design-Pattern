package structural

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

/*
* Adapter Pattern :
* - Convert the interface of a class into another interface the client expect.
* - Convert the data from one format to another format.
* */


// 3rd party functionality : Which you can't modify.
data class DisplayDataType(val index: Float, val data: String)

class DataDisplay {
    fun displayData(data: DisplayDataType) {
        println("Data is displayed: ${data.index} - ${data.data}")
    }
}

//-----------------------
// Our Code

data class DatabaseData(val position: Int, val amount: Int)

class DatabaseDataGenerator {
    fun generateData(): List<DatabaseData> {
        val list = arrayListOf<DatabaseData>()
        list.add(DatabaseData(2, 2))
        list.add(DatabaseData(3, 7))
        list.add(DatabaseData(4, 23))
        return list
    }
}

interface DatabaseDataConverter {
    fun convertData(data: List<DatabaseData>): List<DisplayDataType>
}

class DataDisplayAdapter(private val display: DataDisplay) : DatabaseDataConverter {

    override fun convertData(data: List<DatabaseData>): List<DisplayDataType> {
        val returnList = arrayListOf<DisplayDataType>()
        for (datum in data) {
            val ddt = DisplayDataType(datum.position.toFloat(), datum.amount.toString())
            display.displayData(ddt)
            returnList.add(ddt)
        }
        return returnList
    }
}

class AdapterTest {

    @Test
    fun adapterTest() {
        val generator = DatabaseDataGenerator()
        val generatedData = generator.generateData()

        val adapter = DataDisplayAdapter(DataDisplay())
        val converter = adapter.convertData(generatedData)

        Assertions.assertThat(converter.size).isEqualTo(3)
        Assertions.assertThat(converter[1].index).isEqualTo(3f)
        Assertions.assertThat(converter[1].data).isEqualTo("7")
    }
}