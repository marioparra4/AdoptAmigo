package unam.fca.adoptamigo.bd

import android.content.*
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteQueryBuilder
import android.net.Uri

class MascotasProvider: ContentProvider() {

    val matcher = UriMatcher(UriMatcher.NO_MATCH)
    private lateinit var dataBaseHelper: DataBaseHelper
    private lateinit var db: SQLiteDatabase

    private var columnasTabla = arrayOf(
        Contrato.Mascotas.COLUMNA_ID,
        Contrato.Mascotas.COLUMNA_NOMBRE,
        Contrato.Mascotas.COLUMNA_RAZA,
        Contrato.Mascotas.COLUMNA_EDAD,
        Contrato.Mascotas.COLUMNA_GENERO,
        Contrato.Mascotas.COLUMNA_UBICACION,
        Contrato.Mascotas.COLUMNA_COLOR,
        Contrato.Mascotas.COLUMNA_DESCRIPCION,
        Contrato.Mascotas.COLUMNA_NOMBRECONTACTO,
        Contrato.Mascotas.COLUMNA_TELEFONOCONTACTO,
        Contrato.Mascotas.COLUMNA_CORREOCONTACTO
    )

    override fun onCreate(): Boolean {
        matcher.addURI(Contrato.Mascotas.AUTORIDAD,
            Contrato.Mascotas.TABLA_MASCOTAS,
            Contrato.Mascotas.MASCOTAS)
        matcher.addURI(Contrato.Mascotas.AUTORIDAD,
            Contrato.Mascotas.TABLA_MASCOTAS+"#",
            Contrato.Mascotas.MASCOTAS_ID)

        dataBaseHelper = DataBaseHelper(context as Context)
        db = dataBaseHelper.writableDatabase
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        if(projection != null && !columnasTabla.asList()
                .containsAll(projection.asList())) {
            throw IllegalArgumentException("No se encontraron las columnas")
        }
        val queryBuilder = SQLiteQueryBuilder()
        queryBuilder.tables = Contrato.Mascotas.TABLA_MASCOTAS
        val matcherUriType = matcher.match(uri)
        when(matcherUriType) {
            Contrato.Mascotas.MASCOTAS -> { }
            Contrato.Mascotas.MASCOTAS_ID -> queryBuilder.appendWhere(
                Contrato.Mascotas.COLUMNA_ID + "="+
                        uri.lastPathSegment
            )
        }
        db = dataBaseHelper.readableDatabase
        val cursor = queryBuilder.query(db, projection, selection,
            selectionArgs, null, null, sortOrder)
        cursor.setNotificationUri(context!!.contentResolver, uri)
        return cursor
    }

    override fun getType(uri: Uri): String? {
        TODO("Not yet implemented")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        db = dataBaseHelper.writableDatabase
        val matchedUriType = matcher.match(uri)
        var newId: Long = 0
        when(matchedUriType) {
            Contrato.Mascotas.MASCOTAS ->
                newId = db.insert(Contrato.Mascotas.TABLA_MASCOTAS,
                    null, values)
            else -> throw IllegalArgumentException("URI desconocido: $uri")
        }
        return ContentUris.withAppendedId(uri, newId)
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        var rowDeleted = 0
        db = dataBaseHelper.writableDatabase
        val matchedUriType = matcher.match(uri)
        when(matchedUriType) {
            Contrato.Mascotas.MASCOTAS ->
                rowDeleted = db.delete(Contrato.Mascotas.TABLA_MASCOTAS,
                    selection, selectionArgs)
            Contrato.Mascotas.MASCOTAS_ID -> {
                val idDeleted = uri.lastPathSegment
                if(selection != null && !selection.isEmpty()) {
                    rowDeleted = db.delete(Contrato.Mascotas.TABLA_MASCOTAS,
                        Contrato.Mascotas.COLUMNA_ID +" = "+
                                idDeleted + "AND "+selection, selectionArgs)
                } else {
                    rowDeleted = db.delete(Contrato.Mascotas.TABLA_MASCOTAS,
                        Contrato.Mascotas.COLUMNA_ID +" = "+idDeleted,
                        null)
                }
            }
        }
        return rowDeleted
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        var rowUpdate = 0
        db = dataBaseHelper.writableDatabase
        val matcherUriType = matcher.match(uri)
        when (matcherUriType) {
            Contrato.Mascotas.MASCOTAS -> {}
            Contrato.Mascotas.MASCOTAS_ID -> {
                val idUpdated = uri.lastPathSegment
                rowUpdate = db.update(Contrato.Mascotas.TABLA_MASCOTAS,
                    values, Contrato.Mascotas.COLUMNA_ID +" = "+
                            idUpdated+ " AND "+selection, selectionArgs)
            }
        }
        return rowUpdate
    }
}