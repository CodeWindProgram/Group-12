import React from "react";
import {
  Table,
  TableBody,
  TableHeader,
  TableHeaderColumn,
  TableRow,
  TableRowColumn
} from "material-ui/Table";
import EditIcon from 'material-ui/svg-icons/image/edit';
import TrashIcon from 'material-ui/svg-icons/action/delete';
import CheckIcon from 'material-ui/svg-icons/navigation/check';
import TextField from 'material-ui/TextField';

const row = (x, i, header) => {
  // const currentlyEditing = editIdx === i;
  return (
    <TableRow key={`tr-${i}`} selectable={false}>
      {header.map((y, k) =>
        <TableRowColumn key={`trc-${k}`}>{x[y.prop]}
          {/* {currentlyEditing? (<TextField name={y.prop} onChange={handleChange} value={x[y.prop]}/>) : (x[y.prop])} */}
        </TableRowColumn>
      )}
      {/* <TableRowColumn>
        {currentlyEditing? (<CheckIcon onClick={() => stopEditing()}/>) : (<EditIcon onClick={() => startEditing(i)}/>)}
      </TableRowColumn>
      <TableRowColumn><TrashIcon onClick={() => handleRemove(i)}/></TableRowColumn>  */}
    </TableRow>
  ); 
}

export default ({ data, header }) =>
  <Table>
    <TableHeader>
      <TableRow>
        {header.map((x, i) =>
          <TableHeaderColumn key={`thc-${i}`}>
            {x.name}
          </TableHeaderColumn>
        )}
        {/* <TableHeaderColumn />
        <TableHeaderColumn /> */}
      </TableRow>
    </TableHeader>
    <TableBody>
      {data.map((x, i) => row(x, i, header))}
    </TableBody>
  </Table>;
